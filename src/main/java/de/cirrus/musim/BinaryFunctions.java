package de.cirrus.musim;

import java.math.BigInteger;

public class BinaryFunctions {
   public BinaryFunctions() {
   }

   public static String normalizeStringlengt(String s2, int length) {
      if (!isBinaryString(s2)) {
         return null;
      } else {
         String s1 = s2;
         if (s2.length() > length) {
            return s2.substring(s2.length() - length, s2.length());
         } else {
            while (s1.length() < length) {
               s1 = "0" + s1;
            }

            return s1;
         }
      }
   }

   public static String xor(String s1, String s2) {
      if (!isBinaryString(s1)) {
         return null;
      } else if (!isBinaryString(s2)) {
         return null;
      } else {
         StringPair sp = normalizeStringlengt(new StringPair(s1, s2));
         s1 = sp.getS1();
         s2 = sp.getS2();
         StringBuilder sb = new StringBuilder();

         for (int i = 0; i < s1.length(); ++i) {
            String append = "0";
            String sub1 = s1.substring(i, i + 1);
            String sub2 = s2.substring(i, i + 1);
            if (sub1.equals("0")) {
               if (sub2.equals("1")) {
                  append = "1";
               }
            } else if (sub1.equals("1") && sub2.equals("0")) {
               append = "1";
            }

            sb.append(append);
         }

         return sb.toString();
      }
   }

   public static String and(String s1, String s2) {
      if (!isBinaryString(s1)) {
         return null;
      } else if (!isBinaryString(s2)) {
         return null;
      } else {
         StringPair sp = normalizeStringlengt(new StringPair(s1, s2));
         s1 = sp.getS1();
         s2 = sp.getS2();
         StringBuilder sb = new StringBuilder();

         for (int i = 0; i < s1.length(); ++i) {
            String append = "0";
            String sub1 = s1.substring(i, i + 1);
            String sub2 = s2.substring(i, i + 1);
            if (sub1.equals("1") && sub2.equals("1")) {
               append = "1";
            }

            sb.append(append);
         }

         return sb.toString();
      }
   }

   public static String or(String s1, String s2) {
      if (!isBinaryString(s1)) {
         return null;
      } else if (!isBinaryString(s2)) {
         return null;
      } else {
         StringPair sp = normalizeStringlengt(new StringPair(s1, s2));
         s1 = sp.getS1();
         s2 = sp.getS2();
         StringBuilder sb = new StringBuilder();

         for (int i = 0; i < s1.length(); ++i) {
            String append = "0";
            String sub1 = s1.substring(i, i + 1);
            String sub2 = s2.substring(i, i + 1);
            if (sub1.equals("0")) {
               if (sub2.equals("1")) {
                  append = "1";
               }
            } else if (sub1.equals("1")) {
               append = "1";
            }

            sb.append(append);
         }

         return sb.toString();
      }
   }

   public static String negate(String s1, int resultlength) {
      if (!isBinaryString(s1)) {
         return null;
      } else {
         while (s1.length() < resultlength) {
            s1 = "0" + s1;
         }

         return negate(s1);
      }
   }

   public static String negate(String s1) {
      if (!isBinaryString(s1)) {
         return null;
      } else {
         StringBuilder sb = new StringBuilder();

         for (int i = 0; i < s1.length(); ++i) {
            String append = "0";
            String sub1 = s1.substring(i, i + 1);
            if (sub1.equals("0")) {
               append = "1";
            }

            sb.append(append);
         }

         return sb.toString();
      }
   }

   public static String twoK(String s1, int resultlength) {
      return !isBinaryString(s1) ? null
            : increment(negate(normalizeStringlengt(s1, resultlength), resultlength), resultlength, new Flag(false));
   }

   public static String add(String Op1, String Op2, int resultlength, Flag carry) {
      if (!isBinaryString(Op1)) {
         return null;
      } else if (!isBinaryString(Op2)) {
         return null;
      } else {
         char[] op1 = normalizeStringlengt(Op1, resultlength).toCharArray();
         char[] op2 = normalizeStringlengt(Op2, resultlength).toCharArray();
         char[] result = op1;
         boolean smallcarry = false;
         int i = op2.length;

         while (true) {
            while (i > 0) {
               --i;
               if (op2[i] == '0' && op1[i] == '0') {
                  if (smallcarry) {
                     result[i] = '1';
                     smallcarry = false;
                  }
               } else if (op1[i] != op2[i]) {
                  if (!smallcarry) {
                     result[i] = '1';
                  } else {
                     smallcarry = true;
                     result[i] = '0';
                  }
               } else if (op2[i] == '1' && op1[i] == '1') {
                  if (smallcarry) {
                     result[i] = '1';
                  } else {
                     smallcarry = true;
                     result[i] = '0';
                  }
               }
            }

            if (carry != null) {
               carry.setFlag(smallcarry);
            }

            return new String(result);
         }
      }
   }

   public static String subTwoK(String Op1, String Op2, int resultlength, Flag carry) {
      return add(Op1, twoK(Op2, resultlength), resultlength, carry);
   }

   public static StringPair normalizeStringlengt(StringPair sp) {
      String s1 = sp.getS1();
      String s2 = sp.getS2();
      boolean swaped = false;
      if (s1.length() > s2.length()) {
         String tmp = s1;
         s1 = s2;
         s2 = tmp;
         swaped = true;
      }

      while (s1.length() < s2.length()) {
         s1 = "0" + s1;
      }

      return swaped ? new StringPair(s2, s1) : new StringPair(s1, s2);
   }

   public static String increment(String s1, int resultlength, Flag dst_carrie) {
      if (!isBinaryString(s1)) {
         return null;
      } else {
         boolean smallCarrie = true;
         char[] sb = normalizeStringlengt(s1, resultlength).toCharArray();

         char c;
         for (int i = sb.length; i > 0 && smallCarrie; sb[i] = c) {
            --i;
            c = sb[i];
            if (c == '0') {
               ++c;
               smallCarrie = false;
            } else if (c == '1') {
               --c;
            }
         }

         if (dst_carrie != null) {
            dst_carrie.setFlag(smallCarrie);
         }

         return new String(sb);
      }
   }

   public static boolean isBinaryString(String stringToCheck) {
      boolean b = true;
      char[] arr$ = stringToCheck.toCharArray();
      int len$ = arr$.length;

      for (int it = 0; it < len$; ++it) {
         char c = arr$[it];
         if (c != '0' && c != '1') {
            b = false;
            break;
         }
      }

      return b;
   }

   public static String hexToBinary(String string) {
      BigInteger bigInteger = new BigInteger(string, 16);
      bigInteger.toString(2);
      return bigInteger.toString(2);
   }
}
