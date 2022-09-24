package de.cirrus.musim;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollableComponentArchitecureAdapter extends JPanel {
      JScrollPane scrollPane;
      RAM ram = null;
      JPanel jp;
      JPanel nonscrollablePart;
      Component SchrollablerComponent;

      public ScrollableComponentArchitecureAdapter(RAM r) {
            this.ram = r;
            this.setLayout(new BoxLayout(this, 2));
            this.setBorder(BorderFactory.createEmptyBorder());
            this.jp = new JPanel();
            this.jp.setBorder(BorderFactory.createEmptyBorder());
            this.jp.setLayout(new BoxLayout(this.jp, 3));
            if (r instanceof ScrollAndNonScrollableArchitectureObjectPart) {
                  CompontentScrollAndNonScrollableArchitectureObjectPartAdapter adapter = new CompontentScrollAndNonScrollableArchitectureObjectPartAdapter(
                              r);
                  Component NotScrollableCompontent = adapter.getNonScrollableArchitectureObjectPartAdapter();
                  this.add(NotScrollableCompontent);
                  this.SchrollablerComponent = adapter.getScrollableArchitectureObjectPartAdapter();
                  System.out.println("size NotScrollableCompontent" + NotScrollableCompontent.getPreferredSize());
                  this.jp.add(this.SchrollablerComponent);
                  System.out.println("size jp" + this.jp.getPreferredSize());
                  this.scrollPane = new JScrollPane(this.jp);
                  this.scrollPane.setPreferredSize(new Dimension(this.SchrollablerComponent.getPreferredSize().width
                              + this.scrollPane.getVerticalScrollBar().getPreferredSize().getSize().width + 3, 200));
                  this.scrollPane.setMinimumSize(new Dimension(this.SchrollablerComponent.getPreferredSize().width
                              + this.scrollPane.getVerticalScrollBar().getPreferredSize().getSize().width + 3, 200));
                  System.out.println("size scrollPane" + this.scrollPane.getPreferredSize());
            } else {
                  this.SchrollablerComponent = new ComponentArchitectureAdapter(this.ram);
                  this.jp.add(this.SchrollablerComponent);
                  this.scrollPane = new JScrollPane(this.jp);
                  this.scrollPane.setPreferredSize(new Dimension(this.SchrollablerComponent.getPreferredSize().width
                              + this.scrollPane.getVerticalScrollBar().getPreferredSize().getSize().width + 3, 200));
                  this.scrollPane.setMinimumSize(this.scrollPane.getPreferredSize());
                  System.out.println("size scrollPane" + this.scrollPane.getPreferredSize());
            }

            this.add(this.scrollPane);
            System.out.println(this.getPreferredSize());
            this.ram.addDimensionChangedListeners(new DimensionChangedListener() {
                  public void OnDimensionChanged(ArchtekturObject sender) {
                        ScrollableComponentArchitecureAdapter.this.SchrollablerComponent.invalidate();
                        ScrollableComponentArchitecureAdapter.this.scrollPane.setPreferredSize(new Dimension(
                                    ScrollableComponentArchitecureAdapter.this.SchrollablerComponent
                                                .getPreferredSize().width
                                                + ScrollableComponentArchitecureAdapter.this.scrollPane
                                                            .getVerticalScrollBar()
                                                            .getPreferredSize().getSize().width
                                                + 3,
                                    200));
                        ScrollableComponentArchitecureAdapter.this.scrollPane.setMinimumSize(new Dimension(
                                    ScrollableComponentArchitecureAdapter.this.SchrollablerComponent
                                                .getPreferredSize().width
                                                + ScrollableComponentArchitecureAdapter.this.scrollPane
                                                            .getVerticalScrollBar()
                                                            .getPreferredSize().getSize().width
                                                + 3,
                                    200));
                        ScrollableComponentArchitecureAdapter.this.scrollPane.validate();
                        ScrollableComponentArchitecureAdapter.this.scrollPane.repaint();
                        Dimension d = new Dimension();
                        Component[] arr$ = ScrollableComponentArchitecureAdapter.this.getComponents();
                        int len$ = arr$.length;

                        for (int it = 0; it < len$; ++it) {
                              Component component = arr$[it];
                              d.setSize(d.width + component.getWidth(), d.height + component.getHeight());
                        }

                        ScrollableComponentArchitecureAdapter.this.setBounds(
                                    ScrollableComponentArchitecureAdapter.this.getBounds().x,
                                    ScrollableComponentArchitecureAdapter.this.getBounds().y, d.width,
                                    ScrollableComponentArchitecureAdapter.this.getPreferredSize().height);
                        ScrollableComponentArchitecureAdapter.this.getParent().validate();
                  }
            });
            this.SchrollablerComponent.addMouseListener(new MouseListener() {
                  public void mouseClicked(MouseEvent e) {
                        ScrollableComponentArchitecureAdapter.this.ram.nextViewBase();
                  }

                  public void mousePressed(MouseEvent e) {
                  }

                  public void mouseReleased(MouseEvent e) {
                  }

                  public void mouseEntered(MouseEvent e) {
                  }

                  public void mouseExited(MouseEvent e) {
                  }
            });
      }

      public void setPreferredSize(Dimension preferredSize) {
            super.setPreferredSize(preferredSize);
            this.scrollPane.setPreferredSize(new Dimension(preferredSize.width, preferredSize.height));
      }
}
