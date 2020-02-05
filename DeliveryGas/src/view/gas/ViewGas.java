package view.gas;

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

import controller.ControllerFornecedor;
import controller.ControllerGas;
import model.entities.Fornecedor;
import model.entities.Gas;

public class ViewGas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTipo;
	private JTextField txtValor;

	/**
	 * Launch the application.
	 */
	public void NewViewGas() {
		try {
			ViewGas dialog = new ViewGas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewGas() {
		ControllerFornecedor fornecedorController = new ControllerFornecedor();
		ControllerGas gasController = new ControllerGas();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(12, 39, 55, 16);
		contentPanel.add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(12, 59, 104, 20);
		contentPanel.add(txtTipo);
		txtTipo.setColumns(10);
		
		JLabel btnValor = new JLabel("Valor");
		btnValor.setBounds(145, 39, 55, 16);
		contentPanel.add(btnValor);
		
		txtValor = new JTextField();
		txtValor.setBounds(145, 59, 104, 20);
		contentPanel.add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblCNPJFornecedor = new JLabel("CNPJ do Fornecedor");
		lblCNPJFornecedor.setBounds(282, 39, 128, 16);
		contentPanel.add(lblCNPJFornecedor);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(12, 195, 98, 26);
		contentPanel.add(btnInserir);
		
		JButton btnSelecionarTodos = new JButton("Selecionar Todos");
		btnSelecionarTodos.setBounds(151, 195, 136, 26);
		contentPanel.add(btnSelecionarTodos);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(326, 195, 98, 26);
		contentPanel.add(btnExcluir);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(174, 233, 98, 26);
		contentPanel.add(btnAtualizar);
		
		JComboBox comboBoxCNPJFornecedor = new JComboBox();
		comboBoxCNPJFornecedor.setBounds(282, 57, 128, 25);
		List<Fornecedor> listFornecedor = fornecedorController.findAll();
		
		for(Fornecedor percorrer : listFornecedor){
			comboBoxCNPJFornecedor.addItem(percorrer.getCNPJ());
		}
		
		contentPanel.add(comboBoxCNPJFornecedor);
		{
			btnInserir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Fornecedor fornecedor = fornecedorController.findByCNPJ(comboBoxCNPJFornecedor.getSelectedItem().toString());
					Gas gas = new Gas(0, txtTipo.getText(), Double.parseDouble(txtValor.getText()), fornecedor);
					if(gasController.insert(gas) == true){
						JOptionPane.showMessageDialog(null, "Gas inserido", "Gas", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Gas nao inserido", "Gas", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnSelecionarTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewGasSelect viewGasSelect = new ViewGasSelect();
					viewGasSelect.NewViewGasSelect();
				}
			});
			
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewGasDelete viewGasDelete = new ViewGasDelete();
					viewGasDelete.NewViewGasDelete();
				}
			});
			
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewGasUpdate viewGasUpdate = new ViewGasUpdate();
					viewGasUpdate.NewViewGasUpdate();
				}
			});
		}
	}

}
