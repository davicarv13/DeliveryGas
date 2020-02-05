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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.ControllerEndereco;
import model.entities.Endereco;
import java.awt.Dimension;

public class ViewEndereco extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCidade;
	private JTextField txtCep;
	private JTextField txtBairro;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JButton btnInserir;
	private JButton btnSelecionaTodos;
	private JButton btnExcluir;
	private JButton btnAtualizar;

	/**
	 * Launch the application.
	 */
	public void NewViewEndereco() {
		try {
			ViewEndereco dialog = new ViewEndereco();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEndereco() {
		ControllerEndereco controllerEndereco = new ControllerEndereco();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setMaximumSize(new Dimension(60001, 60001));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			contentPanel.setLayout(null);
			JLabel lblCidade = new JLabel("Cidade");
			lblCidade.setBounds(12, 12, 40, 16);
			contentPanel.add(lblCidade);
			
			txtCidade = new JTextField();
			txtCidade.setBounds(12, 35, 104, 20);
			contentPanel.add(txtCidade);
			txtCidade.setColumns(10);
			
			JLabel lblCep = new JLabel("CEP");
			lblCep.setBounds(160, 12, 25, 16);
			contentPanel.add(lblCep);
			
			txtCep = new JTextField();
			txtCep.setBounds(160, 35, 104, 20);
			contentPanel.add(txtCep);
			txtCep.setColumns(10);
			
			JLabel lblBairro = new JLabel("Bairro");
			lblBairro.setBounds(160, 67, 36, 16);
			contentPanel.add(lblBairro);
			
			txtBairro = new JTextField();
			txtBairro.setBounds(160, 95, 104, 20);
			contentPanel.add(txtBairro);
			txtBairro.setColumns(10);
			
			JLabel lblRua = new JLabel("Rua");
			lblRua.setBounds(12, 67, 23, 16);
			contentPanel.add(lblRua);
			
			txtRua = new JTextField();
			txtRua.setBounds(12, 95, 104, 20);
			contentPanel.add(txtRua);
			txtRua.setColumns(10);
			
			JLabel lblNumero = new JLabel("Numero");
			lblNumero.setBounds(295, 12, 46, 16);
			contentPanel.add(lblNumero);
			
			txtNumero = new JTextField();
			txtNumero.setBounds(295, 35, 104, 20);
			contentPanel.add(txtNumero);
			txtNumero.setColumns(10);
			
			JButton btnInserir = new JButton("Inserir");
			btnInserir.setBackground(UIManager.getColor("Button.shadow"));
			btnInserir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Endereco endereco = new Endereco(0, txtRua.getText(), txtCep.getText(), 
							txtCidade.getText(), Integer.parseInt(txtNumero.getText()), txtBairro.getText());
					if(controllerEndereco.insert(endereco) == true){
						JOptionPane.showMessageDialog(null, "Endereco inserido", "Endereco", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Endereco nao inserido", "Endereco", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnInserir.setBounds(12, 183, 71, 26);
			
			contentPanel.add(btnInserir);
			btnSelecionaTodos = new JButton("Seleciona Todos");
			btnSelecionaTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewEnderecoSelect viewEnderecoSelect = new ViewEnderecoSelect(); 
					viewEnderecoSelect.NewViewEnderecoSelect();
				}
			});
			btnSelecionaTodos.setBounds(134, 183, 128, 26);
			contentPanel.add(btnSelecionaTodos);
			
			btnExcluir = new JButton("Excluir");
			btnExcluir.setBackground(UIManager.getColor("Button.shadow"));
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewEnderecoDelete viewEnderecoDelete = new ViewEnderecoDelete(); 
					viewEnderecoDelete.NewViewEnderecoDelete();
				}
			});
			
			btnExcluir.setBounds(315, 183, 74, 26);
			contentPanel.add(btnExcluir);
			
			btnAtualizar = new JButton("Atualizar");
			btnAtualizar.setBounds(160, 220, 85, 26);
			btnAtualizar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					ViewEnderecoUpdate viewEnderecoUpdate = new ViewEnderecoUpdate(); 
					viewEnderecoUpdate.NewViewEnderecoUpdate();
				}
			});
			contentPanel.add(btnAtualizar);
		}
	}
}
