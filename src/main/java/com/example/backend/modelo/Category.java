package com.example.backend.modelo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/*Con estas lineas hacemos que Java reconcozca la clase Category como
//si fuera una tabla, la informacion que hay en las tablas de la BD
se van a representar con instancias de las clases */
//Para poder pasar la informacion por este medio hay que heredar implements Serializable
//Aqui se definen las Clases Entidad para coonstruir las tablas de la Base de Datos.
//que si creamos en Mysql, el JPA es el que crea las tablas
@Entity
@Table(name = "Category") //Aqui se indica que Category es una tabla
public class Category implements Serializable {
    //Le indicamos a java que la llave primaria es el id
    @Id
    //Par aque sea auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Definimos los campos de la tabla
    private Integer id;
    private String name;
    private String description;
    //Se establecen las relaciones con la tabla Computer.
    // Se indica que si se modifica algo en una tabla continue con ascadeType.PERSIST
    //Se le indica que cada uno de los Computers lo va ha encontrar con el campo categoriaId
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "category")
    /*Le indicamos a la tabla Category como se va a llamar la llave foranea
    Con esta sentencia se genera una recursion infinita, por que cada llamado a
    Categorias vuelve y retorna la lista de Computeres que estan en esa Categoria*/
    private List<Computer> computers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }
}