package view.pedido;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerCliente;
import controller.ControllerEndereco;
import controller.ControllerEntregador;
import controller.ControllerGas;
import controller.ControllerPedido;
import model.entities.Cliente;
import model.entities.Endereco;
import model.entities.Entregador;
import model.entities.Gas;
import model.entities.Pedido;

public class ViewPedido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtQtde;

	/**
	 * Launch the application.
	 */
	public void NewViewPedido() {
		try {
			ViewPedido dialog = new ViewPedido();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewPedido() {
		ControllerCliente clienteController = new ControllerCliente();
		ControllerEndereco enderecoController = new ControllerEndereco();
		ControllerEntregador entregadorController = new ControllerEntregador();
		ControllerGas gasController = new ControllerGas();
		ControllerPedido pedidoController = new ControllerPedido();
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(12, 204, 136, 26);
		contentPanel.add(btnInserir);
		
		JButton btnSelecionarTodos = new JButton("Selecionar Todos");
		btnSelecionarTodos.setBounds(169, 204, 136, 26);
		contentPanel.add(btnSelecionarTodos);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(326, 204, 136, 26);
		contentPanel.add(btnExcluir);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(169, 242, 136, 26);
		contentPanel.add(btnAtualizar);
		
		JLabel lblCodEndEntrega = new JLabel("Selecione o código do endereco de entrega");
		lblCodEndEntrega.setBounds(30, 0, 260, 16);
		contentPanel.add(lblCodEndEntrega);
		
		JComboBox comboBoxCodEndEntrega = new JComboBox();
		comboBoxCodEndEntrega.setBounds(30, 28, 80, 25);
		
		List<Endereco> listEndereco = enderecoController.findAll();
		for(Endereco percorrer : listEndereco){
			comboBoxCodEndEntrega.addItem(percorrer.getCodEndereco());
		}
		
		contentPanel.add(comboBoxCodEndEntrega);
		
		JLabel lblCPFCliente = new JLabel("Selecione o CPF do cliente");
		lblCPFCliente.setBounds(30, 65, 162, 16);
		contentPanel.add(lblCPFCliente);
		
		JComboBox comboBoxCPFCliente = new JComboBox();
		comboBoxCPFCliente.setBounds(30, 93, 162, 25);
		
		List<Cliente> listCliente = clienteController.findAll();
		for(Cliente percorrer : listCliente){
			comboBoxCPFCliente.addItem(percorrer.getCpf());
		}
		
		contentPanel.add(comboBoxCPFCliente);
		
		JLabel lblCPFEntregador = new JLabel("Selecione o CPF do entregador");
		lblCPFEntregador.setBounds(267, 65, 187, 16);
		contentPanel.add(lblCPFEntregador);
		
		JComboBox comboBoxCPFEntregador = new JComboBox();
		comboBoxCPFEntregador.setBounds(267, 93, 187, 25);
		
		List<Entregador> listEntregador = entregadorController.findAll();
		for(Entregador percorrer : listEntregador){
			comboBoxCPFEntregador.addItem(percorrer.getCpf());
		}
		
		contentPanel.add(comboBoxCPFEntregador);
		
		JLabel lblCodGas = new JLabel("Selecione o código do gás");
		lblCodGas.setBounds(30, 130, 162, 16);
		contentPanel.add(lblCodGas);
		
		JComboBox comboBoxCodGas = new JComboBox();
		comboBoxCodGas.setBounds(30, 158, 80, 25);
		List<Gas> listGas = gasController.findAll();
		for(Gas percorrer : listGas){
			comboBoxCodGas.addItem(percorrer.getCodGas());
		}
		contentPanel.add(comboBoxCodGas);
		
		JLabel lblQtde = new JLabel("Informe a quantidade");
		lblQtde.setBounds(267, 130, 130, 16);
		contentPanel.add(lblQtde);
		
		txtQtde = new JTextField();
		txtQtde.setBounds(267, 160, 119, 20);
		contentPanel.add(txtQtde);
		txtQtde.setColumns(10);
		
		JButton btnEntregarPedido = new JButton("Entregar Pedido");
		btnEntregarPedido.setBounds(12, 242, 136, 26);
		contentPanel.add(btnEntregarPedido);
		
		JButton btnCancelarPedido = new JButton("Cancelar Pedido");
		btnCancelarPedido.setBounds(326, 242, 136, 26);
		contentPanel.add(btnCancelarPedido);
		{

			btnInserir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Endereco enderecoEntrega = enderecoController.findById((Integer)comboBoxCodEndEntrega.getSelectedItem());
					Cliente cliente = clienteController.findByCPF(comboBoxCPFCliente.getSelectedItem().toString());
					Entregador entregador = entregadorController.findByCPF(comboBoxCPFEntregador.getSelectedItem().toString());
					Gas gas = gasController.findById((Integer)comboBoxCodGas.getSelectedItem());
					
					double valorTotal = gas.getValor() * Integer.parseInt(txtQtde.getText());
					
					Calendar c = Calendar.getInstance();
					
					int ano = c.get(Calendar.YEAR);
					int mes = c.get(Calendar.MONTH);
					int dia = c.get(Calendar.DAY_OF_MONTH);
					
					String dataExpedicao = ano + "-" + mes + "-" + dia;
					
					Pedido pedido = new Pedido(0, dataExpedicao, null, "PENDENTE", valorTotal, entregador, cliente,  enderecoEntrega);
					
					if(pedidoController.insert(pedido) == true){
						JOptionPane.showMessageDialog(null, "Pedido inserido", "Pedido", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Pedido nao inserido", "Pedido", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			btnSelecionarTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewPedidoSelect viewPedidoSelect = new ViewPedidoSelect();
					viewPedidoSelect.NewViewPedidoSelect();
				}
			});
			
			btnEntregarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewPedidoEntrega viewPedidoEntrega = new ViewPedidoEntrega();
					viewPedidoEntrega.NewViewPedidoEntrega();
				}
			});
			
			btnCancelarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewPedidoCancelar viewPedidoCancelar = new ViewPedidoCancelar();
					viewPedidoCancelar.NewViewPedidoCancelar();
				}
			});
			
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewPedidoDelete viewPedidoDelete = new ViewPedidoDelete();
					viewPedidoDelete.NewViewPedidoDelete();
				}
			});
			
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewPedidoUpdate viewPedidoUpdate = new ViewPedidoUpdate();
					viewPedidoUpdate.NewViewPedidoUpdate();
				}
			});
		}
	}
}
