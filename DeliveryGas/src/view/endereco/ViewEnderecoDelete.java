package view.endereco;

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

import controller.ControllerEndereco;

public class ViewEnderecoDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodEndereco;

	/**
	 * Launch the application.
	 */
	public void NewViewEnderecoDelete() {
		try {
			ViewEnderecoDelete dialog = new ViewEnderecoDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEnderecoDelete() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			contentPanel.setLayout(null);
			
			txtCodEndereco = new JTextField();
			txtCodEndereco.setBounds(169, 102, 104, 20);
			contentPanel.add(txtCodEndereco);
			txtCodEndereco.setColumns(10);
			JLabel lblCodEndereco = new JLabel("Informe o código do endereco");
			lblCodEndereco.setBounds(138, 74, 169, 16);
			contentPanel.add(lblCodEndereco);
			
			JButton btnExcluir = new JButton("Excluir");
			btnExcluir.setBounds(186, 134, 74, 26);
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ControllerEndereco controllerEndereco = new ControllerEndereco();
					if(controllerEndereco.deleteByID(Integer.parseInt(txtCodEndereco.getText())) == true){
						JOptionPane.showMessageDialog(null, "Endereco apagado", "Endereco", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Codigo invalido", "Endereco", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			contentPanel.add(btnExcluir);
		}
	}

}
