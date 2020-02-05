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
import model.entities.Endereco;

public class ViewEnderecoUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCidade;
	private JTextField txtCep;
	private JTextField txtBairro;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JButton btnAtualizar;
	private JLabel lblCodEndereco;
	private JTextField txtCodEndereco;

	/**
	 * Launch the application.
	 */
	public void NewViewEnderecoUpdate() {
		try {
			ViewEnderecoUpdate dialog = new ViewEnderecoUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEnderecoUpdate() {
		ControllerEndereco controllerEndereco = new ControllerEndereco();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			JLabel lblCidade = new JLabel("Cidade");
			lblCidade.setBounds(12, 33, 55, 16);
			contentPanel.add(lblCidade);
			
			txtCidade = new JTextField();
			txtCidade.setBounds(12, 51, 104, 20);
			contentPanel.add(txtCidade);
			txtCidade.setColumns(10);
			
			JLabel lblCep = new JLabel("CEP");
			lblCep.setBounds(155, 33, 55, 16);
			contentPanel.add(lblCep);
			
			txtCep = new JTextField();
			txtCep.setBounds(155, 51, 104, 20);
			contentPanel.add(txtCep);
			txtCep.setColumns(10);
			
			JLabel lblBairro = new JLabel("Bairro");
			lblBairro.setBounds(295, 33, 55, 16);
			contentPanel.add(lblBairro);
			
			txtBairro = new JTextField();
			txtBairro.setBounds(294, 51, 104, 20);
			contentPanel.add(txtBairro);
			txtBairro.setColumns(10);
			
			JLabel lblRua = new JLabel("Rua");
			lblRua.setBounds(12, 120, 55, 16);
			contentPanel.add(lblRua);
			
			txtRua = new JTextField();
			txtRua.setBounds(12, 137, 104, 20);
			contentPanel.add(txtRua);
			txtRua.setColumns(10);
			
			JLabel lblNumero = new JLabel("Numero");
			lblNumero.setBounds(155, 120, 55, 16);
			contentPanel.add(lblNumero);
			
			txtNumero = new JTextField();
			txtNumero.setBounds(155, 137, 104, 20);
			contentPanel.add(txtNumero);
			txtNumero.setColumns(10);
			
			btnAtualizar = new JButton("Atualizar");
			btnAtualizar.setBounds(178, 187, 98, 26);
			btnAtualizar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Endereco endereco = new Endereco(Integer.parseInt(txtCodEndereco.getText()), txtRua.getText(), txtCep.getText(), 
							txtCidade.getText(), Integer.parseInt(txtNumero.getText()), txtBairro.getText());
					if(controllerEndereco.update(endereco) == true){
						JOptionPane.showMessageDialog(null, "Endereco atualizado", "Endereco", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "CodEndereco nao encontrado", "Endereco", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			contentPanel.add(btnAtualizar);
			
			lblCodEndereco = new JLabel("CodEndereco");
			lblCodEndereco.setBounds(295, 120, 104, 16);
			contentPanel.add(lblCodEndereco);
			
			txtCodEndereco = new JTextField();
			txtCodEndereco.setBounds(294, 137, 104, 20);
			contentPanel.add(txtCodEndereco);
			txtCodEndereco.setColumns(10);
		}
	}

}
