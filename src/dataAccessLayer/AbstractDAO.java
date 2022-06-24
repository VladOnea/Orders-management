package dataAccessLayer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * Abstract class for data access
 * @param <T> The class the specific database table will work with
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ordermanagementapp.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    private String selectAllFrom(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ordermanagementapp.");
        sb.append(type.getSimpleName());
        return sb.toString();
    }
    private String insertInto(T t) throws IllegalAccessException {
        StringBuilder sb= new StringBuilder();
        sb.append("INSERT INTO ordermanagementapp.");
        sb.append(type.getSimpleName());
        sb.append(" (");
        for(Field field:type.getDeclaredFields()){
            String fieldName=field.getName();
            if(fieldName.compareTo("id")!=0){
                sb.append(fieldName);
                sb.append(",");
            }
        }
        sb=new StringBuilder(sb.toString().substring(0,sb.length()-1));
        sb.append(") Values (");
        for(Field field:type.getDeclaredFields()){
            field.setAccessible(true);
            String fieldName=field.getName();
            if(fieldName.compareTo("id")!=0){
                sb.append("'"+field.get(t).toString()+"'");
                sb.append(",");
            }
        }
        sb=new StringBuilder(sb.toString().substring(0,sb.length()-1));
        sb.append(");");
        return sb.toString();
    }
    private String deleteFrom(int id){
        StringBuilder sb= new StringBuilder();
        sb.append("DELETE FROM ordermanagementapp.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id='"+id+"';");
        return sb.toString();
    }

    private String updateSet(T t,int id) throws IllegalAccessException {
        StringBuilder sb= new StringBuilder();
        sb.append("UPDATE ordermanagementapp.");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for(Field field:type.getDeclaredFields()){
            field.setAccessible(true);
            String fieldName=field.getName();
            if(fieldName.compareTo("id")!=0){
                sb.append(fieldName);
                sb.append("='"+field.get(t)+"',");
            }
        }
        sb=new StringBuilder(sb.toString().substring(0,sb.length()-1));
        sb.append(" WHERE id='"+id+"';");
        return sb.toString();
    }



    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = selectAllFrom();
        List<T> result=null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            result= createObjects(resultSet);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }


        return result;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            try{
                T result= createObjects(resultSet).get(0);
                ConnectionFactory.close(resultSet);
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
                return result;
            }catch(Exception exception){

            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Method that inserts the object t's field values into the database
     * @param t the object to be inserted
     * @return the inserted object
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String query = insertInto(t);
            //  System.out.println(query);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     *  Method that updates the object t's field from id in the database
     * @param t the object to be updated
     * @param id at which id the action is performed
     * @return the updated object
     */
    public T update(T t,int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String query = updateSet(t,id);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }


        return t;
    }

    /**
     * Method that deletes
     * @param id at which id the action is performed
     */
    public void delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String query = deleteFrom(id);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
