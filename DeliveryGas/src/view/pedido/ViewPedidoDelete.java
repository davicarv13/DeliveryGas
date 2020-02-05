package view.pedido;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerPedido;
import model.entities.Pedido;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class ViewPedidoDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControllerPedido pedidoController = new ControllerPedido();

	/**
	 * Launch the application.
	 */
	public void NewViewPedidoDelete() {
		try {
			ViewPedidoDelete dialog = new ViewPedidoDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewPedidoDelete() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblSelecioneOCdigo = new JLabel("Selecione o código do pedido");
		lblSelecioneOCdigo.setBounds(141, 59, 178, 16);
		contentPanel.add(lblSelecioneOCdigo);
		
		JComboBox comboBoxCodPedido = new JComboBox();
		comboBoxCodPedido.setBounds(169, 87, 110, 25);
		contentPanel.add(comboBoxCodPedido);
		
		JButton btnExcluirPedido = new JButton("Excluir");
		btnExcluirPedido.setBounds(169, 123, 110, 26);
		contentPanel.add(btnExcluirPedido);
		{
			atualizaComboBox(comboBoxCodPedido);
			
			btnExcluirPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pedidoController.deleteById((Integer)comboBoxCodPedido.getSelectedItem()) == true){
						atualizaComboBox(comboBoxCodPedido);
						JOptionPane.showMessageDialog(null, "Pedido excluido", "Pedido", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Pedido nao excluido", "Pedido", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}
	
	public void atualizaComboBox(JComboBox comboBoxCodPedido){
		comboBoxCodPedido.removeAllItems();
		List<Pedido> listPedido = pedidoController.findAll();
		
		for(Pedido percorrer : listPedido){
			comboBoxCodPedido.addItem(percorrer.getCodPedido());
		}
	}

}
