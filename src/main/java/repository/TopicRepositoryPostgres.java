package repository;

import exception.AddException;
import exception.GetException;
import exception.RemoveException;
import exception.UpdateException;
import model.Topic;
import repository.dao.TopicRepository;
import service.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicRepositoryPostgres implements TopicRepository {

    private static final String SELECT_All = "SELECT * FROM public.topic";
    private static final String GET =
            """
                    SELECT * FROM public.topic
                    WHERE id = ?
                    """;
    private static final String SAVE =
            """
                    INSERT INTO public.topic(name)
                    VALUES (?)
                    """;
    private static final String REMOVE =
            """
                     DELETE FROM public.topic
                    WHERE id = ?
                    """;
    private static final String UPDATE =
            """
                    UPDATE public.topic
                    SET name = ?
                    WHERE  id = ?
                    """;

    @Override
    public List<Topic> getAll() {
        try (PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(SELECT_All)){
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Topic> topics = new ArrayList<>();
            while (resultSet.next()) {
                Topic build = Topic.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();
                topics.add(build);
            }
            return topics;
        } catch (SQLException ex) {
            throw new GetException("Get all topic error!" + ex.getMessage());
        }
    }

    @Override
    public boolean add(Topic topic) {
        try (PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(SAVE)){
            preparedStatement.setString(1, topic.getName());
            return preparedStatement.execute();
        } catch (SQLException ex) {
            throw new AddException("Add topic error!" + ex.getMessage());
        }
    }

    @Override
    public Topic get(int id) {
        try(PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(GET)) {
            preparedStatement.execute();
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return Topic.builder()
                    .name(resultSet.getString("name"))
                    .id(resultSet.getInt("id"))
                    .build();

        } catch (SQLException ex) {
            throw new GetException("Get topic by id error!" + ex.getMessage());
        }
    }

    @Override
    public boolean remove(int id) {
        try (PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(REMOVE)){
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException ex) {
            throw new RemoveException("Remove topic by id error!" + ex.getMessage());
        }
    }

    @Override
    public int update(Topic topic) {
        try (PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(UPDATE)){
            preparedStatement.setString(1, topic.getName());
            preparedStatement.setInt(2, topic.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new UpdateException("Update topic error!" + ex.getMessage());
        }
    }
}
