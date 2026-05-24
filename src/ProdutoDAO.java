import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO {

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        String sql = "INSERT INTO TB_PRODUTO (CODIGO, NOME, PRECO) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, produto.getCodigo());
            stm.setString(2, produto.getNome());
            stm.setDouble(3, produto.getPreco());
            return stm.executeUpdate();
        }
    }

    @Override
    public Integer atualizar(Produto produto) throws Exception {
        String sql = "UPDATE TB_PRODUTO SET NOME = ?, PRECO = ? WHERE CODIGO = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getPreco());
            stm.setString(3, produto.getCodigo());
            return stm.executeUpdate();
        }
    }

    @Override
    public Produto buscar(String codigo) throws Exception {
        String sql = "SELECT ID, CODIGO, NOME, PRECO FROM TB_PRODUTO WHERE CODIGO = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, codigo);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return mapProduto(rs);
                }
                return null;
            }
        }
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        String sql = "SELECT ID, CODIGO, NOME, PRECO FROM TB_PRODUTO ORDER BY ID";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                produtos.add(mapProduto(rs));
            }
        }

        return produtos;
    }

    @Override
    public Integer excluir(Produto produto) throws Exception {
        String sql = "DELETE FROM TB_PRODUTO WHERE CODIGO = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, produto.getCodigo());
            return stm.executeUpdate();
        }
    }

    private Produto mapProduto(ResultSet rs) throws Exception {
        Produto produto = new Produto();
        produto.setId(rs.getLong("ID"));
        produto.setCodigo(rs.getString("CODIGO"));
        produto.setNome(rs.getString("NOME"));
        produto.setPreco(rs.getDouble("PRECO"));
        return produto;
    }
}
