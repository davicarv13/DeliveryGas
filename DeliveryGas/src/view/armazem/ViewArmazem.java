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

public class ViewArmazem extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtCidade;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtNumero;

	/**
	 * Launch the application.
	 */
	public void NewViewArmazem() {
		try {
			ViewArmazem dialog = new ViewArmazem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewArmazem() {
		ControllerArmazem armazemController = new ControllerArmazem();
		ControllerEndereco endereController = new ControllerEndereco();
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
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(12, 197, 98, 26);
		contentPanel.add(btnInserir);
		
		JButton btnSelecionar = new JButton("Selecionar Todos");
		btnSelecionar.setBounds(145, 197, 137, 26);
		contentPanel.add(btnSelecionar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(300, 197, 98, 26);
		contentPanel.add(btnExcluir);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(167, 235, 98, 26);
		contentPanel.add(btnAtualizar);
		{
	
			btnInserir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Endereco endereco = new Endereco(0, txtRua.getText(), txtCep.getText(), 
							txtCidade.getText(), Integer.parseInt(txtNumero.getText()), txtBairro.getText());
					if(endereController.insert(endereco) == false){
						JOptionPane.showMessageDialog(null, "Endereco nao pôde ser inserido", "Endereco", JOptionPane.ERROR_MESSAGE);
					}
					else{
						Armazem armazem = new Armazem(0, txtNome.getText(), endereco);
						if(armazemController.insert(armazem) == true){
							JOptionPane.showMessageDialog(null, "Armazem inserido", "Armazem", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(null, "Armazem nao inserido", "Armazem", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			
			btnSelecionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewArmazemSelect viewArmazemSelect = new ViewArmazemSelect();
					viewArmazemSelect.NewViewArmazemSelect();
				}
			});
			
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewArmazemDelete viewArmazemDelete = new ViewArmazemDelete();
					viewArmazemDelete.NewViewArmazemDelete();
				}
			});
			
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewArmazemUpdate viewArmazemUpdate = new ViewArmazemUpdate();
					viewArmazemUpdate.NewViewArmazemUpdate();
				}
			});
		}
	}

}
