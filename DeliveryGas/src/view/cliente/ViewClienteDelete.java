package view.cliente;

import java.awt.BorderLayout;
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

import controller.ControllerCliente;
import model.entities.Cliente;

public class ViewClienteDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public void NewViewClienteDelete() {
		try {
			ViewClienteDelete dialog = new ViewClienteDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewClienteDelete() {
		ControllerCliente clienteController = new ControllerCliente();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodCliente = new JLabel("Selecione o CPF do Cliente");
		lblCodCliente.setBounds(133, 80, 174, 16);
		contentPanel.add(lblCodCliente);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(169, 143, 98, 26);
		contentPanel.add(btnExcluir);
		
		JComboBox comboBoxCpf = new JComboBox();
		comboBoxCpf.setBounds(103, 108, 239, 25);
		List<Cliente> listCliente = clienteController.findAll();
		
		for(Cliente percorrer : listCliente){
			comboBoxCpf.addItem(percorrer.getCpf());
		}
		
		contentPanel.add(comboBoxCpf);
		{
			
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object obj = comboBoxCpf.getSelectedItem();
					if(clienteController.deleteByCpf(obj.toString()) == true){
						JOptionPane.showMessageDialog(null, "Cliente excluído", "Cliente", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Cliente não excluído", "Cliente", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
		}
	}
}
