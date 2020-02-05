package view.estoque;

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

import controller.ControllerArmazem;
import controller.ControllerEstoque;
import controller.ControllerGas;
import model.entities.Armazem;
import model.entities.Estoque;
import model.entities.Gas;

public class ViewEstoque extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtQtde;

	/**
	 * Launch the application.
	 */
	public void NewViewEstoque() {
		try {
			ViewEstoque dialog = new ViewEstoque();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEstoque() {
		ControllerArmazem armazemController = new ControllerArmazem();
		ControllerGas gasController = new ControllerGas();
		ControllerEstoque estoqueController = new ControllerEstoque();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodGas = new JLabel("Selecione o código do gás");
		lblCodGas.setBounds(12, 12, 148, 16);
		contentPanel.add(lblCodGas);
		
		JComboBox comboBoxCodGas = new JComboBox();
		comboBoxCodGas.setBounds(12, 40, 148, 25);
		
		List<Gas> listGas = gasController.findAll();
		for(Gas percorrer : listGas){
			comboBoxCodGas.addItem(percorrer.getCodGas());
		}
		contentPanel.add(comboBoxCodGas);
		
		JLabel lblCodArmazem = new JLabel("Selecione o código do armazém");
		lblCodArmazem.setBounds(206, 12, 187, 16);
		contentPanel.add(lblCodArmazem);
		
		JComboBox comboBoxCodArmazem = new JComboBox();
		comboBoxCodArmazem.setBounds(206, 40, 187, 25);
		List<Armazem> listArmazem = armazemController.findAll();
		for(Armazem percorrer : listArmazem){
			comboBoxCodArmazem.addItem(percorrer.getCodArmazem());
		}
		contentPanel.add(comboBoxCodArmazem);
		
		JLabel llblQtde = new JLabel("Informe a quantidade de gás");
		llblQtde.setBounds(12, 96, 167, 16);
		contentPanel.add(llblQtde);
		
		txtQtde = new JTextField();
		txtQtde.setBounds(12, 124, 167, 20);
		contentPanel.add(txtQtde);
		txtQtde.setColumns(10);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(12, 178, 98, 26);
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Armazem armazem = armazemController.findById((Integer) comboBoxCodArmazem.getSelectedItem());
				Gas gas = gasController.findById((Integer)comboBoxCodGas.getSelectedItem());
				Estoque estoque = new Estoque(0, Integer.parseInt(txtQtde.getText()), armazem, gas);
				if(estoqueController.insert(estoque) == true){
					JOptionPane.showMessageDialog(null, "Estoque cadastrado", "Estoque", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Estoque não cadastrado", "Estoque", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPanel.add(btnInserir);
		
		JButton btnSelecionarTodos = new JButton("Selecionar Todos");
		btnSelecionarTodos.setBounds(151, 178, 136, 26);
		btnSelecionarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEstoqueSelect viewEstoqueSelect = new ViewEstoqueSelect(); 
				viewEstoqueSelect.NewViewEstoqueSelect();
			}
		});
		contentPanel.add(btnSelecionarTodos);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(326, 178, 98, 26);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEstoqueDelete viewEstoqueDelete = new ViewEstoqueDelete(); 
				viewEstoqueDelete.NewViewEstoqueDelete();
			}
		});
		contentPanel.add(btnExcluir);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(174, 216, 98, 26);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEstoqueUpdate viewEstoqueUpdate = new ViewEstoqueUpdate(); 
				viewEstoqueUpdate.NewViewEstoqueUpdate();
			}
		});
		contentPanel.add(btnAtualizar);
	}
}
