package view.pedido;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerPedido;
import model.entities.Gas;
import model.entities.Pedido;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ViewPedidoEntrega extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControllerPedido pedidoController = new ControllerPedido();

	/**
	 * Launch the application.
	 */
	public void NewViewPedidoEntrega() {
		try {
			ViewPedidoEntrega dialog = new ViewPedidoEntrega();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewPedidoEntrega() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEntrega = new JLabel("Selecione o código do pedido a ser entregue");
		lblEntrega.setBounds(75, 55, 277, 16);
		contentPanel.add(lblEntrega);
		
		JComboBox comboBoxCodPedido = new JComboBox();
		comboBoxCodPedido.setBounds(146, 83, 100, 25);
		contentPanel.add(comboBoxCodPedido);
		
		JButton btnEntregar = new JButton("Entregar");
		btnEntregar.setBounds(146, 120, 98, 26);
		contentPanel.add(btnEntregar);
		{
			atualizaComboBox(comboBoxCodPedido);
			
			btnEntregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pedido pedido = pedidoController.findById((Integer)comboBoxCodPedido.getSelectedItem());
					Calendar c = Calendar.getInstance();
					
					int ano = c.get(Calendar.YEAR);
					int mes = c.get(Calendar.MONTH);
					int dia = c.get(Calendar.DAY_OF_MONTH);
					
					String dataEntrega = ano + "-" + mes + "-" + dia;
					
					pedido.setData_Entrega(dataEntrega);
					
					if(pedidoController.entregarPedido(pedido) == true){
						atualizaComboBox(comboBoxCodPedido);
						JOptionPane.showMessageDialog(null, "Pedido entrege", "Pedido", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Pedido não entregue", "Pedido", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}
	
	public void atualizaComboBox(JComboBox comboBoxCodPedido){
		comboBoxCodPedido.removeAllItems();
		List<Pedido> listPedido = pedidoController.findAllPedidosPendentes();
		
		for(Pedido percorrer : listPedido){
			comboBoxCodPedido.addItem(percorrer.getCodPedido());
		}
	}
}
