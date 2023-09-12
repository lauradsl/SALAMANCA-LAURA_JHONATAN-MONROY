package com.backend.pruebaclinicaOdontologica.entity;


import javax.persistence.*;



@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ODONTOLOGO_ID")
    private Long id;
    private String matricula;
    private String nombre;
    private String apellido;

    //RELACION UNIDIRECCIONAL
    //@OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    //@JoinColumn(name = "turno_id")
    //private List<Turno> turnos = new ArrayList<>();

    public Odontologo() {}

    public Odontologo(String Matricula, String nombre, String apellido) {
        this.matricula = Matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
