package repository;

import exception.AddException;
import exception.GetException;
import exception.RemoveException;
import exception.UpdateException;
import model.Question;
import repository.dao.QuestionRepository;
import service.ConnectionService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuestionRepositoryPostgres implements QuestionRepository {

    private static final String SELECT_All = "SELECT * FROM public.question";
    private static final String GET =
            """
                    SELECT * FROM public.question
                    WHERE id = ?
                    """;
    private static final String SAVE =
            """
                    INSERT INTO public.question(text,topic_id)
                    VALUES (?,?)
                    """;
    private static final String REMOVE =
            """
                    DELETE FROM public.question
                    WHERE id = ?
                    """;
    private static final String UPDATE =
            """
                    UPDATE public.question
                    SET text = ?, topic_id = ?
                    WHERE  id = ?
                    """;

    @Override
    public List<Question> getAll() {
        try (PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(SELECT_All)){
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                Question build = Question.builder()
                        .text(resultSet.getString(2))
                        .id(resultSet.getInt(1))
                        .topic_id(resultSet.getInt(3))
                        .build();
                questions.add(build);
            }
            return questions;
        } catch (SQLException ex) {
            throw new GetException("Get all question error!" + ex.getMessage());
        }
    }

    @Override
    public boolean add(Question question) {
        try (PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(SAVE)){
            preparedStatement.setString(1, question.getText());
            preparedStatement.setInt(2, question.getTopic_id());
            return preparedStatement.execute();
        } catch (SQLException ex) {
            throw new AddException("Add question error!" + ex.getMessage());
        }
    }

    @Override
    public Question get(int id) {
        try (PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(GET)){
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return Question.builder()
                    .text(resultSet.getString("text"))
                    .id(resultSet.getInt("id"))
                    .build();
        } catch (SQLException ex) {
            throw new GetException("Get question by id error!" + ex.getMessage());
        }
    }

    @Override
    public boolean remove(int id) {
        try (PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(REMOVE)){
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException ex) {
            throw new RemoveException("Remove question by id error!" + ex.getMessage());
        }
    }

    @Override
    public int update(Question question) {
        try (PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(UPDATE)){
            preparedStatement.setString(1, question.getText());
            preparedStatement.setInt(2, question.getTopic_id());
            preparedStatement.setInt(3, question.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new UpdateException("Update question error!" + ex.getMessage());
        }
    }
}

