package view.pedido;

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

import controller.ControllerCliente;
import controller.ControllerEndereco;
import controller.ControllerEntregador;
import controller.ControllerGas;
import controller.ControllerPedido;
import model.entities.Endereco;
import model.entities.Entregador;
import model.entities.Gas;
import model.entities.Pedido;

public class ViewPedidoUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtQtde;

	/**
	 * Launch the application.
	 */
	public void NewViewPedidoUpdate() {
		try {
			ViewPedidoUpdate dialog = new ViewPedidoUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewPedidoUpdate() {
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
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(169, 218, 136, 26);
		contentPanel.add(btnAtualizar);
		
		JLabel lblCodEndEntrega = new JLabel("Selecione o código do endereco de entrega");
		lblCodEndEntrega.setBounds(226, 0, 260, 16);
		contentPanel.add(lblCodEndEntrega);
		
		JComboBox comboBoxCodEndEntrega = new JComboBox();
		comboBoxCodEndEntrega.setBounds(225, 22, 80, 25);
		
		List<Endereco> listEndereco = enderecoController.findAll();
		for(Endereco percorrer : listEndereco){
			comboBoxCodEndEntrega.addItem(percorrer.getCodEndereco());
		}
		
		contentPanel.add(comboBoxCodEndEntrega);
		
		JLabel lblCPFEntregador = new JLabel("Selecione o CPF do entregador");
		lblCPFEntregador.setBounds(226, 75, 187, 16);
		contentPanel.add(lblCPFEntregador);
		
		JComboBox comboBoxCPFEntregador = new JComboBox();
		comboBoxCPFEntregador.setBounds(226, 103, 187, 25);
		
		List<Entregador> listEntregador = entregadorController.findAll();
		for(Entregador percorrer : listEntregador){
			comboBoxCPFEntregador.addItem(percorrer.getCpf());
		}
		
		contentPanel.add(comboBoxCPFEntregador);
		
		JLabel lblCodGas = new JLabel("Selecione o código do gás");
		lblCodGas.setBounds(12, 75, 162, 16);
		contentPanel.add(lblCodGas);
		
		JComboBox comboBoxCodGas = new JComboBox();
		comboBoxCodGas.setBounds(12, 103, 80, 25);
		List<Gas> listGas = gasController.findAll();
		for(Gas percorrer : listGas){
			comboBoxCodGas.addItem(percorrer.getCodGas());
		}
		contentPanel.add(comboBoxCodGas);
		
		JLabel lblQtde = new JLabel("Informe a quantidade");
		lblQtde.setBounds(12, 140, 130, 16);
		contentPanel.add(lblQtde);
		
		JLabel lblSelecioneOCdigo = new JLabel("Selecione o código do pedido");
		lblSelecioneOCdigo.setBounds(12, 0, 180, 16);
		contentPanel.add(lblSelecioneOCdigo);
		
		JComboBox comboBoxCodPedido = new JComboBox();
		comboBoxCodPedido.setBounds(12, 22, 86, 25);
		
		List<Pedido> listPedido = pedidoController.findAllPedidosPendentes();
		for(Pedido percorrer : listPedido){
			comboBoxCodPedido.addItem(percorrer.getCodPedido());
		}
		
		contentPanel.add(comboBoxCodPedido);
		
		txtQtde = new JTextField();
		txtQtde.setBounds(12, 168, 104, 20);
		contentPanel.add(txtQtde);
		txtQtde.setColumns(10);
		{
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pedido pedido = pedidoController.findById((Integer)comboBoxCodPedido.getSelectedItem());
					Endereco enderecoEntrega = enderecoController.findById((Integer)comboBoxCodEndEntrega.getSelectedItem());
					Entregador entregador = entregadorController.findByCPF(comboBoxCPFEntregador.getSelectedItem().toString());
					Gas gas = gasController.findById((Integer)comboBoxCodGas.getSelectedItem());
					pedido.setEnderecoEntrega(enderecoEntrega);
					pedido.setEntregador(entregador);
					pedido.setValor_Total(gas.getValor() * Integer.parseInt(txtQtde.getText()));
					
					if(pedidoController.update(pedido) == true){
						JOptionPane.showMessageDialog(null, "Pedido atualizado", "Pedido", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Pedido nao atualizado", "Pedido nao atualizado", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
		}
	}
}
