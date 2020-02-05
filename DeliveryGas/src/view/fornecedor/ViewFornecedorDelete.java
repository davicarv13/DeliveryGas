package view.fornecedor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerFornecedor;

public class ViewFornecedorDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodFornecedor;

	/**
	 * Launch the application.
	 */
	public void NewViewFornecedorDelete() {
		try {
			ViewFornecedorDelete dialog = new ViewFornecedorDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewFornecedorDelete() {
		ControllerFornecedor fornecedorController = new ControllerFornecedor();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodFornecedor = new JLabel("Insira o código do Fornecedor");
		lblCodFornecedor.setBounds(133, 80, 174, 16);
		contentPanel.add(lblCodFornecedor);
		
		txtCodFornecedor = new JTextField();
		txtCodFornecedor.setBounds(157, 108, 114, 20);
		contentPanel.add(txtCodFornecedor);
		txtCodFornecedor.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(167, 136, 98, 26);
		contentPanel.add(btnExcluir);
		{
			
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(fornecedorController.deleteByID(Integer.parseInt(txtCodFornecedor.getText())) == true){
						JOptionPane.showMessageDialog(null, "Fornecedor excluído", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Fornecedor não excluído", "Fornecedor", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
		}
	}
}
