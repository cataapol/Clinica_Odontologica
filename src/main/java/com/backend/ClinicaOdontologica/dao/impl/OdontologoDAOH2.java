package com.backend.ClinicaOdontologica.dao.impl;


import com.backend.ClinicaOdontologica.dao.IDao;
import com.backend.ClinicaOdontologica.dbconnection.H2Connection;
import com.backend.ClinicaOdontologica.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo> {

    Logger LOGGER = LoggerFactory.getLogger(OdontologoDAOH2.class);


    @Override
    public Odontologo registrar(Odontologo odontologo) {

        Connection connection = null;
        Odontologo odontologoRegistrado = null;

        String INSERT = "INSERT INTO ODONTOLOGOS(NROMATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            


            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, odontologo.getNroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());


            preparedStatement.execute();


            odontologoRegistrado = new Odontologo(odontologo.getNroMatricula(), odontologo.getNombre(), odontologo.getApellido());




            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                odontologoRegistrado.setId(resultSet.getInt("id"));
            }


            connection.commit();
            LOGGER.info("Se registró el nuevo odontólogo: " + odontologoRegistrado);




        }catch(Exception e){

            LOGGER.error(e.getMessage());
            e.printStackTrace();


            if(connection!= null) {

                try {
                    connection.rollback();
                    LOGGER.error("Hubo un problema.. " + e.getMessage());
                    e.printStackTrace();

                } catch (SQLException eSQL) {
                    LOGGER.error(eSQL.getMessage());
                    eSQL.printStackTrace();
                }

            }


        }finally {


            try {
                connection.close();

            } catch (Exception e) {

                LOGGER.error("tuvimos un problema... " + e.getMessage());
                e.printStackTrace();


            }
        }


        return odontologoRegistrado;
    }



    //--------------------------------------------------------------------------



    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologoList = new ArrayList<>();
        Connection connection = null;

        //QUERY
        String SELECT_TODOS = "SELECT * FROM ODONTOLOGOS";


        try {

            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODOS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Odontologo odontologo = crearObjetoOdontologo(resultSet);
                odontologoList.add(odontologo);


            }

            LOGGER.info("Se encontraron los siguientes odontólogos: " + odontologoList);



        } catch (Exception e){

            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {

            try{
                connection.close();

            } catch(Exception e){

                LOGGER.error("tuvimos un problema... " + e.getMessage());
                e.printStackTrace();

            }

        }


        return odontologoList ;
    }


    @Override
    public Odontologo buscarPorId(int id) {
        return null;
    }

    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {

        return new Odontologo(resultSet.getInt("nroMatricula"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"));


    }
}
