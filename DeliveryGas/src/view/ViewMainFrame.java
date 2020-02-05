package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ControllerScreen;
import view.armazem.ViewArmazem;
import view.cliente.ViewCliente;
import view.endereco.ViewEndereco;
import view.entregador.ViewEntregador;
import view.estoque.ViewEstoque;
import view.fornecedor.ViewFornecedor;
import view.gas.ViewGas;
import view.pedido.ViewPedido;

public class ViewMainFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void NewViewMainFrame() {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
	    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
	    	System.err.println(ex);
	    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMainFrame window = new ViewMainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewMainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setSize(new Dimension(1000, 580));
		frame.getContentPane().setBackground(new Color(238, 238, 238));
		ControllerScreen screenController = new ControllerScreen();
		frame.setBounds(0, 0, screenController.getWidth(), screenController.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnEndereco = new JButton("Manter endereco");
		btnEndereco.setBounds(118, 40, 141, 26);
		btnEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEndereco viewEndereco = new ViewEndereco();
				viewEndereco.NewViewEndereco();
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnEndereco);
		
		JButton btnManterArmazem = new JButton("Manter Armazem");
		btnManterArmazem.setBounds(118, 86, 141, 26);
		btnManterArmazem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewArmazem viewArmazem = new ViewArmazem();
				viewArmazem.NewViewArmazem();
			}
		});
		frame.getContentPane().add(btnManterArmazem);
		
		JButton btnManterFornecedor = new JButton("Manter Fornecedor");
		btnManterFornecedor.setBounds(118, 136, 141, 26);
		btnManterFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewFornecedor viewFornecedor = new ViewFornecedor();
				viewFornecedor.NewViewFornecedor();
			}
		});
		frame.getContentPane().add(btnManterFornecedor);
		
		JLabel lblTitulo = new JLabel("Página Principal");
		lblTitulo.setBounds(293, 12, 124, 16);
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 14));
		frame.getContentPane().add(lblTitulo);
		
		JButton btnManterGas = new JButton("Manter Gas");
		btnManterGas.setBounds(440, 40, 141, 26);
		btnManterGas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewGas viewGas = new ViewGas();
				viewGas.NewViewGas();
			}
		});
		frame.getContentPane().add(btnManterGas);
		
		JButton btnManterCliente = new JButton("Manter Cliente");
		btnManterCliente.setBounds(118, 181, 141, 26);
		btnManterCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCliente viewCliente = new ViewCliente();
				viewCliente.NewViewCliente();
			}
		});
		frame.getContentPane().add(btnManterCliente);
		
		JButton btnManterEstoque = new JButton("Manter Estoque");
		btnManterEstoque.setBounds(440, 86, 141, 26);
		btnManterEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEstoque viewEstoque = new ViewEstoque();
				viewEstoque.NewViewEstoque();
			}
		});
		frame.getContentPane().add(btnManterEstoque);
		
		JButton btnManterEntregador = new JButton("Manter Entregador");
		btnManterEntregador.setBounds(440, 136, 141, 26);
		btnManterEntregador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEntregador viewEntregador = new ViewEntregador();
				viewEntregador.NewViewEntregador();
			}
		});
		frame.getContentPane().add(btnManterEntregador);
		
		JButton btnManterPedido = new JButton("Manter Pedido");
		btnManterPedido.setBounds(440, 181, 141, 26);
		btnManterPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPedido viewPedido = new ViewPedido();
				viewPedido.NewViewPedido();
			}
		});
		frame.getContentPane().add(btnManterPedido);
	}
}
