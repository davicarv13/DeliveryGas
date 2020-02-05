package view.armazem;

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

import controller.ControllerArmazem;
import controller.ControllerEndereco;
import model.entities.Armazem;
import model.entities.Endereco;

public class ViewArmazemUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtCidade;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JTextField txtCodEndereco;

	/**
	 * Launch the application.
	 */
	public void NewViewArmazemUpdate() {
		try {
			ViewArmazemUpdate dialog = new ViewArmazemUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewArmazemUpdate() {
		ControllerArmazem armazemController = new ControllerArmazem();
		ControllerEndereco enderecoController = new ControllerEndereco();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 12, 55, 16);
		contentPanel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(12, 29, 104, 20);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(12, 61, 55, 16);
		contentPanel.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(12, 89, 104, 20);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(145, 61, 55, 16);
		contentPanel.add(lblCep);
		
		txtCep = new JTextField();
		txtCep.setBounds(145, 89, 104, 20);
		contentPanel.add(txtCep);
		txtCep.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(279, 61, 55, 16);
		contentPanel.add(lblRua);
		
		txtRua = new JTextField();
		txtRua.setBounds(279, 89, 104, 20);
		contentPanel.add(txtRua);
		txtRua.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(12, 121, 55, 16);
		contentPanel.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(12, 149, 104, 20);
		contentPanel.add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(145, 121, 55, 16);
		contentPanel.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(145, 149, 104, 20);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(169, 216, 98, 26);
		contentPanel.add(btnAtualizar);
		
		JLabel lblCodArmazem = new JLabel("Codigo do armazém");
		lblCodArmazem.setBounds(279, 121, 124, 16);
		contentPanel.add(lblCodArmazem);
		
		txtCodEndereco = new JTextField();
		txtCodEndereco.setBounds(279, 149, 104, 20);
		contentPanel.add(txtCodEndereco);
		txtCodEndereco.setColumns(10);
		
		{
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Armazem armazem = armazemController.findById(Integer.parseInt(txtCodEndereco.getText()));
					Endereco endereco = new Endereco(armazem.getEndereco().getCodEndereco(), txtRua.getText(), txtCep.getText(), 
							txtCidade.getText(), Integer.parseInt(txtNumero.getText()), txtBairro.getText());
					armazem.setNome(txtNome.getText());
					armazem.setEndereco(endereco);
					
					if(enderecoController.update(endereco) == false){
						JOptionPane.showMessageDialog(null, "Endereço não pôde ser atualizado", "Endereco", JOptionPane.ERROR_MESSAGE);
					}
					else{
						if(armazemController.update(armazem) == true){
							JOptionPane.showMessageDialog(null, "Armazem atualizado", "Armazem", JOptionPane.INFORMATION_MESSAGE);
						}
						else{	
							JOptionPane.showMessageDialog(null, "Armazem nao atualizado", "Armazem", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}
	}

}
