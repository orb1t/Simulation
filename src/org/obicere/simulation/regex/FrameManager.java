package org.obicere.simulation.regex;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Obicere
 */
public class FrameManager {

    private static final Color BAD_PATTERN  = new Color(128, 0, 0);
    private static final Color GOOD_PATTERN = new Color(0, 128, 0);

    public FrameManager() {

        final JFrame frame = new JFrame("Regex Fractals");

        final Canvas canvas = new Canvas();
        final JPanel controls = new JPanel();

        final JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 15, 1));
        final JTextField regex = new JTextField(20);
        final JButton graph = new JButton("Graph");

        regex.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            private void update() {
                try {
                    final Pattern pattern = Pattern.compile(regex.getText());
                    Objects.requireNonNull(pattern);
                    regex.setForeground(GOOD_PATTERN);
                } catch (final Exception e) {
                    regex.setForeground(BAD_PATTERN);
                }
            }

        });

        graph.addActionListener(e -> canvas.applyRegex((Integer) sizeSpinner.getValue(), regex.getText()));

        final BoxLayout layout = new BoxLayout(controls, BoxLayout.LINE_AXIS);

        controls.setLayout(layout);
        controls.add(sizeSpinner);
        controls.add(regex);
        controls.add(Box.createHorizontalGlue());
        controls.add(graph);

        frame.add(canvas, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
