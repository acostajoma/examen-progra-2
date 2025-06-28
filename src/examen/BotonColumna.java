/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author joseacosta
 * Clase de utilidad para renderizar y manejar clics en botones dentro de un JTable.
 * Es una combinación de un TableCellRenderer y un TableCellEditor.
 */

public class BotonColumna extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener{
    private JTable table;
    private Action action;
    private Border originalBorder;
    private Border focusBorder;
    private JButton renderButton;
    private JButton editButton;
    private Object editorValue;
    private boolean isButtonColumnEditor;

    public BotonColumna(JTable table, Action action, int column) {
        this.table = table;
        this.action = action;

        // 
        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);
        originalBorder = editButton.getBorder();
        setFocusBorder(BorderFactory.createLineBorder(Color.BLUE));

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Border getOriginalBorder() {
        return originalBorder;
    }

    public void setOriginalBorder(Border originalBorder) {
        this.originalBorder = originalBorder;
    }

    public JButton getRenderButton() {
        return renderButton;
    }

    public void setRenderButton(JButton renderButton) {
        this.renderButton = renderButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public Object getEditorValue() {
        return editorValue;
    }

    public void setEditorValue(Object editorValue) {
        this.editorValue = editorValue;
    }

    public boolean isIsButtonColumnEditor() {
        return isButtonColumnEditor;
    }

    public void setIsButtonColumnEditor(boolean isButtonColumnEditor) {
        this.isButtonColumnEditor = isButtonColumnEditor;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public ChangeEvent getChangeEvent() {
        return changeEvent;
    }

    public void setChangeEvent(ChangeEvent changeEvent) {
        this.changeEvent = changeEvent;
    }
    

    public void setFocusBorder(Border focusBorder) {
        this.focusBorder = focusBorder;
        editButton.setBorder(focusBorder);
    }
    
    // Definir qué texto o ícono mostrar en el botón que se está dibujando.
    private JButton setearValorDeBoton (JButton boton, Object value){
        // Asigna el ícono o texto al botón que se va a "editar" (el que está activo)
        if (value == null) {
            // Si el valor de la celda es nulo entonces no setea contenido ni icono
            boton.setText("");
            boton.setIcon(null);
        } else if (value instanceof Icon) {
            // Si es instancia de Icono (columnas 4 y 5) agrega el icono sin texto
            boton.setText("");
            boton.setIcon((Icon) value);
        } else {
            // De otra manera setea value al texto de la celda
            boton.setText(value.toString());
            boton.setIcon(null);
        }
        return boton;
    }
    
    /**
     * Prepara y devuelve el componente (en este caso, un botón) 
     * que se mostrará dentro de la celda cuando el usuario haga 
     * clic para editarla.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        setearValorDeBoton(editButton, value);
        this.editorValue = value;
        return editButton;
    }

    // Retorna un valor
    @Override
    public Object getCellEditorValue() {
        return editorValue;
    }

    /**
     * Responsable de como se ve una celda en su estado normal
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Si la fila completa está seleccionada, toma los colores de selección 
        // de la tabla y los aplica al fondo y texto del botón. 
        // Si no, usa los colores por defecto
        if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        // Si la celda tiene el foco,
        // le aplica un borde especial para que resalte
        if (hasFocus) {
            renderButton.setBorder(focusBorder);
        } else {
            renderButton.setBorder(originalBorder);
        }
        
        // Definir qué texto o ícono mostrar en el botón que se está dibujando.
        setearValorDeBoton(renderButton, value);

        return renderButton;
    }
    
    /**
     * Código que se ejecuta exactamente en el momento en que el usuario hace 
     * clic en el botón funcional que apareció gracias al TableCellEditor
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Cuando se hace clic en el botón
        // Obtenemos el numero de fila que se esta editando
        int row = table.convertRowIndexToModel(table.getEditingRow());
        fireEditingStopped(); // Es importante para que la tabla sepa que terminamos de editar
        
        // Creamos un nuevo evento que incluye la fila y la acción a ejecutar
        // Es importante para pasar la responsabilidad de la lógica a otro objeto
        // Y no hacer un "hardcode" de la logica dentro de esta clase
        // Lo importante es que adjuntamos informacion importante en el evento para 
        // poder usarla en otro lugar
        ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, "" + row);
        action.actionPerformed(event);
    }
}
