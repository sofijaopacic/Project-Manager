/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import domen.Constants;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Sofija
 */
public class ProjectsFormRenderer extends JLabel implements TableCellRenderer
{

    JLabel label;

    public ProjectsFormRenderer()
    {
        label = new JLabel();

        label.setOpaque(true);
        label.setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        String text = value.toString();

        label.setText(text);
        label.setFont(table.getFont());

        if (isSelected)
        {
            label.setBackground(table.getSelectionBackground());
            label.setForeground(Color.WHITE);
        } else
        {
            label.setBackground(row % 2 == 0 ? Color.WHITE : new Color(238, 238, 238));
            label.setForeground(text == Constants.PROJECT_ACTIVE ? Color.RED : new Color(0, 155, 0));
        }

        return label;
    }

}
