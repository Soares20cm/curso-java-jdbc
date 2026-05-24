import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        executarCrudCliente();
        executarCrudProduto();
    }

    private static void executarCrudCliente() throws Exception {
        IClienteDAO clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente(null, "12345678901", "Maria Silva", "maria@email.com");
        Cliente clienteExistente = clienteDAO.buscar(cliente.getCpf());
        if (clienteExistente != null) {
            clienteDAO.excluir(clienteExistente);
        }

        clienteDAO.cadastrar(cliente);

        Cliente clienteCadastrado = clienteDAO.buscar(cliente.getCpf());
        System.out.println(clienteCadastrado);

        clienteCadastrado.setNome("Maria Souza");
        clienteCadastrado.setEmail("maria.souza@email.com");
        clienteDAO.atualizar(clienteCadastrado);

        List<Cliente> clientes = clienteDAO.buscarTodos();
        clientes.forEach(System.out::println);

        clienteDAO.excluir(clienteCadastrado);
    }

    private static void executarCrudProduto() throws Exception {
        IProdutoDAO produtoDAO = new ProdutoDAO();

        Produto produto = new Produto(null, "A1", "Teclado Mecanico", 250.0, "Logitech");
        Produto produtoExistente = produtoDAO.buscar(produto.getCodigo());
        if (produtoExistente != null) {
            produtoDAO.excluir(produtoExistente);
        }

        produtoDAO.cadastrar(produto);

        Produto produtoCadastrado = produtoDAO.buscar("A1");
        System.out.println(produtoCadastrado);

        produtoCadastrado.setNome("Teclado RGB");
        produtoCadastrado.setPreco(299.9);
        produtoCadastrado.setMarca("Redragon");
        produtoDAO.atualizar(produtoCadastrado);

        List<Produto> produtos = produtoDAO.buscarTodos();
        produtos.forEach(System.out::println);

        produtoDAO.excluir(produtoCadastrado);
    }
}
