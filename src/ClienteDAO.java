import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {

    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        String sql = "INSERT INTO TB_CLIENTE (NOME, CPF) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCpf());
            return stm.executeUpdate();
        }
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        String sql = "UPDATE TB_CLIENTE SET NOME = ? WHERE CPF = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCpf());
            return stm.executeUpdate();
        }
    }

    @Override
    public Cliente buscar(String cpf) throws Exception {
        String sql = "SELECT ID, NOME, CPF FROM TB_CLIENTE WHERE CPF = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, cpf);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return mapCliente(rs);
                }
                return null;
            }
        }
    }

    @Override
    public List<Cliente> buscarTodos() throws Exception {
        String sql = "SELECT ID, NOME, CPF FROM TB_CLIENTE ORDER BY ID";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                clientes.add(mapCliente(rs));
            }
        }

        return clientes;
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        String sql = "DELETE FROM TB_CLIENTE WHERE CPF = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, cliente.getCpf());
            return stm.executeUpdate();
        }
    }

    private Cliente mapCliente(ResultSet rs) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("ID"));
        cliente.setNome(rs.getString("NOME"));
        cliente.setCpf(rs.getString("CPF"));
        return cliente;
    }
}
