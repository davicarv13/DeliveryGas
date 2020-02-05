package view.entregador;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerEndereco;
import controller.ControllerEntregador;
import model.entities.Endereco;
import model.entities.Entregador;

public class ViewEntregadorUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtCidade;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtSalario;

	/**
	 * Launch the application.
	 */
	public void NewViewEntregadorUpdate() {
		try {
			ViewEntregadorUpdate dialog = new ViewEntregadorUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEntregadorUpdate() {
		ControllerEntregador entregadorController = new ControllerEntregador();
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
		txtNome.setBounds(12, 29, 80, 20);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(12, 125, 55, 16);
		contentPanel.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(12, 139, 104, 20);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(145, 125, 55, 16);
		contentPanel.add(lblCep);
		
		txtCep = new JTextField();
		txtCep.setBounds(145, 139, 104, 20);
		contentPanel.add(txtCep);
		txtCep.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(279, 125, 55, 16);
		contentPanel.add(lblRua);
		
		txtRua = new JTextField();
		txtRua.setBounds(279, 139, 104, 20);
		contentPanel.add(txtRua);
		txtRua.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(12, 168, 55, 16);
		contentPanel.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(12, 183, 104, 20);
		contentPanel.add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(145, 168, 55, 16);
		contentPanel.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(145, 183, 104, 20);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(161, 228, 126, 28);
		contentPanel.add(btnAtualizar);
		
		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setBounds(145, 12, 55, 16);
		contentPanel.add(lblCPF);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(279, 12, 55, 16);
		contentPanel.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(279, 29, 104, 20);
		contentPanel.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(12, 61, 55, 16);
		contentPanel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(12, 76, 104, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSalario = new JTextField();
		txtSalario.setBounds(145, 76, 104, 20);
		contentPanel.add(txtSalario);
		txtSalario.setColumns(10);
		
		JLabel lblSalario = new JLabel("Salário");
		lblSalario.setBounds(145, 61, 55, 16);
		contentPanel.add(lblSalario);
		
		JComboBox comboBoxCPF = new JComboBox();
		comboBoxCPF.setBounds(145, 27, 104, 25);
		contentPanel.add(comboBoxCPF);
		
		List<Entregador> listEntregador = entregadorController.findAll();
		
		for(Entregador percorrer : listEntregador){
			comboBoxCPF.addItem(percorrer.getCpf());
		}
		
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Entregador obj1 = entregadorController.findByCPF(comboBoxCPF.getSelectedItem().toString());
				Endereco obj2 = new Endereco(obj1.getEndereco().getCodEndereco(), txtRua.getText(), txtCep.getText(), 
						txtCidade.getText(), Integer.parseInt(txtNumero.getText()), txtBairro.getText());
				obj1.setNome(txtNome.getText());
				obj1.setTelefone(txtTelefone.getText());
				obj1.setEmail(txtEmail.getText());
				obj1.setSalario(Double.parseDouble(txtSalario.getText()));
				obj1.setEndereco(obj2);
				
				if(enderecoController.update(obj2) == false){
					JOptionPane.showMessageDialog(null, "Endereço não pôde ser atualizado", "Endereco", JOptionPane.ERROR_MESSAGE);
				}
				else{
					if(entregadorController.updateByCPF(obj1) == true){
						JOptionPane.showMessageDialog(null, "Entregador atualizado", "Entregador", JOptionPane.INFORMATION_MESSAGE);
					}
					else{	
						JOptionPane.showMessageDialog(null, "Entregador nao atualizado", "Entregador", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

}
