package view.pedido;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerPedido;
import model.entities.Pedido;

public class ViewPedidoCancelar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControllerPedido pedidoController = new ControllerPedido();
	
	/**
	 * Launch the application.
	 */
	public void NewViewPedidoCancelar() {
		try {
			ViewPedidoCancelar dialog = new ViewPedidoCancelar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewPedidoCancelar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCancelar = new JLabel("Selecione o código do pedido a ser cancelado");
		lblCancelar.setBounds(75, 55, 277, 16);
		contentPanel.add(lblCancelar);
		
		JComboBox comboBoxCodPedido = new JComboBox();
		comboBoxCodPedido.setBounds(146, 83, 128, 25);
		contentPanel.add(comboBoxCodPedido);
		
		JButton btnCancelarr = new JButton("Cancelar Pedido");
		btnCancelarr.setBounds(146, 120, 128, 26);
		contentPanel.add(btnCancelarr);
		{
			atualizaComboBox(comboBoxCodPedido);
			
			btnCancelarr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pedido pedido = pedidoController.findById((Integer)comboBoxCodPedido.getSelectedItem());
					if(pedidoController.cancelarPedido(pedido) == true){
						atualizaComboBox(comboBoxCodPedido);
						JOptionPane.showMessageDialog(null, "Pedido cancelado", "Pedido", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Pedido não cancelado", "Pedido", JOptionPane.ERROR_MESSAGE);
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
