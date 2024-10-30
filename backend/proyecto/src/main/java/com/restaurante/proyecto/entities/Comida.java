package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
/**
 * Clase que representa un plato de comida en el sistema
 * de gestión del menú de un restaurante.
 *
 * Esta entidad almacena la información de un plato, incluyendo
 * su nombre, tipo, descripción, precio, imagen y el local donde se ofrece.
 */
@Entity
@Table(name = "comida")
public class Comida {
    /**
     * Identificador único del plato de comida.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComida")
    private Long idComida;
    /**
     * Nombre del plato de comida.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "nombreComida", nullable = false, length = 50)
    private String nombreComida;
    /**
     * Tipo de comida (por ejemplo, entrada, plato principal, postre).
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "tipoComida", nullable = false, length = 50)
    private String tipoComida;
    /**
     * Descripción breve del plato de comida.
     * Este campo es opcional y tiene un límite de 100 caracteres.
     */
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    /**
     * Precio del plato de comida.
     * Este campo es obligatorio y se define con una precisión de hasta 10 dígitos.
     */
    @Column(name = "precio", nullable = false, precision = 10)
    private double precio;
    /**
     * URL de la imagen que representa el plato de comida.
     * Este campo es opcional y tiene un límite de 255 caracteres.
     */
    @Column(name = "imagenUrl", length = 255) // Nueva columna para guardar la URL de la imagen
    private String imagenUrl;
    /**
     * Relación muchos a uno con la entidad Local.
     * Indica el local donde se ofrece este plato de comida.
     */
    @ManyToOne
    @JoinColumn(name = "idLocal")
    private Local local;
    /**
     * Constructor vacío para la entidad Comida.
     * Utilizado por el framework para la creación de instancias.
     */
    public Comida() {
    }
    /**
     * Constructor con parámetros para crear una instancia de Comida.
     *
     * @param idComida el identificador único del plato de comida
     * @param nombreComida el nombre del plato de comida
     * @param tipoComida el tipo de comida (entrada, plato principal, postre, etc.)
     * @param descripcion la descripción del plato de comida
     * @param precio el precio del plato de comida
     * @param imagenUrl la URL de la imagen representativa del plato
     * @param local el local donde se ofrece el plato de comida
     */
    public Comida(Long idComida, String nombreComida, String tipoComida, String descripcion, double precio, String imagenUrl, Local local) {
        this.idComida = idComida;
        this.nombreComida = nombreComida;
        this.tipoComida = tipoComida;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.local = local;
    }

    /**
     * Obtiene el identificador único del plato de comida.
     *
     * @return el id del plato de comida
     */
    public Long getIdComida() {
        return idComida;
    }
    /**
     * Establece el identificador único del plato de comida.
     *
     * @param idComida el nuevo id del plato de comida
     */
    public void setIdComida(Long idComida) {
        this.idComida = idComida;
    }
    /**
     * Obtiene el nombre del plato de comida.
     *
     * @return el nombre del plato de comida
     */
    public String getNombreComida() {
        return nombreComida;
    }
    /**
     * Establece el nombre del plato de comida.
     *
     * @param nombreComida el nuevo nombre del plato de comida
     */
    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }
    /**
     * Obtiene el tipo de comida (entrada, plato principal, postre, etc.).
     *
     * @return el tipo de comida
     */
    public String getTipoComida() {
        return tipoComida;
    }
    /**
     * Establece el tipo de comida.
     *
     * @param tipoComida el nuevo tipo de comida
     */
    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }
    /**
     * Obtiene la descripción del plato de comida.
     *
     * @return la descripción del plato de comida
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Establece la descripción del plato de comida.
     *
     * @param descripcion la nueva descripción del plato de comida
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Obtiene el precio del plato de comida.
     *
     * @return el precio del plato de comida
     */
    public double getPrecio() {
        return precio;
    }
    /**
     * Establece el precio del plato de comida.
     *
     * @param precio el nuevo precio del plato de comida
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    /**
     * Obtiene la URL de la imagen representativa del plato de comida.
     *
     * @return la URL de la imagen del plato
     */
    public String getImagenUrl() {
        return imagenUrl;
    }
    /**
     * Establece la URL de la imagen representativa del plato de comida.
     *
     * @param imagenUrl la nueva URL de la imagen del plato
     */
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
    /**
     * Obtiene el local donde se ofrece este plato de comida.
     *
     * @return el local asociado
     */
    public Local getLocal() {
        return local;
    }
    /**
     * Establece el local donde se ofrece este plato de comida.
     *
     * @param local el nuevo local asociado
     */
    public void setLocal(Local local) {
        this.local = local;
    }
}
