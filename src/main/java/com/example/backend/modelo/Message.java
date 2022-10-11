package com.example.backend.modelo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*Con estas lineas hacemos que Java reconcozca la clase Message como
//si fuera una tabla, la informacion que hay en las tablas de la BD
se van a representar con instancias de las clases */
//Para poder pasar la informacion por este medio hay que heredar implements Serializable
//Aqui se definen las Clases Entidad para coonstruir las tablas de la Base de Datos.
//que si creamos en Mysql, el JPA es el que crea las tablas
//Este codigo crea la tabla automaticamente en la BD
@Entity
@Table(name = "Message") //Aqui se indica que Message es una tabla
public class Message implements Serializable {
    //Le indicamos a java que la llave primaria es el id
    @Id
    //Par aque sea auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Definimos los campos de la tabla
    private Integer idMessage;
    private String messageText;
    /*Relacion entre Message y Computer como es Muchos a Uno debe tener un campo tipo Computer*/
    @ManyToOne(/*cascade = {CascadeType.PERSIST}, mappedBy = "category" */)
    @JoinColumn(name="computer")
    private Computer computer;
    //Relacion entre Message y Client como es Muchos a Uno debe tener un campo tipo Client*/
    @ManyToOne(/*cascade = {CascadeType.PERSIST}, mappedBy = "category" */)
    @JoinColumn(name="client")
    private Client client;

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
