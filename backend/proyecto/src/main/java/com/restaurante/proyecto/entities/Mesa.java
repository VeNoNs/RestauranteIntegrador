package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
/**
 * Clase que representa una mesa en el sistema de gestión de restaurantes.
 *
 * Una mesa tiene un número, una cantidad de sillas, un estado y está asociada a un local.
 */
@Entity
@Table(name = "mesa")
public class Mesa {
    /**
     * Identificador único de la mesa.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMesa")  // Cambiado a "idMesa" para que coincida con la tabla
    private Long idMesa;
    /**
     * Número de la mesa.
     * Este campo es obligatorio.
     */
    @Column(name = "nroMesa", nullable = false)
    private int nroMesa;
    /**
     * Cantidad de sillas disponibles en la mesa.
     * Este campo es obligatorio.
     */
    @Column(name = "cantidadSillas", nullable = false)
    private int cantidadSillas;
    /**
     * Estado de la mesa (por ejemplo, disponible, ocupada).
     * Este campo es obligatorio y tiene un límite de 20 caracteres.
     */
    @Column(name = "estado", nullable = false, length = 20)  // Cambié el tamaño a 20 para que coincida con la tabla
    private String estado;
    /**
     * Relación con el local al que pertenece la mesa.
     * Este campo es obligatorio.
     */
    @ManyToOne
    @JoinColumn(name = "idLocal", nullable = false)  // Relación con Local
    private Local local;
    /**
     * Constructor vacío para la entidad Mesa.
     * Utilizado por el framework para la creación de instancias.
     */
    public Mesa() {
    }
    /**
     * Constructor con parámetros para crear una instancia de Mesa.
     *
     * @param idMesa el identificador único de la mesa
     * @param nroMesa el número de la mesa
     * @param cantidadSillas la cantidad de sillas en la mesa
     * @param estado el estado de la mesa
     * @param local el local al que pertenece la mesa
     */
    public Mesa(Long idMesa, int nroMesa, int cantidadSillas, String estado, Local local) {
        this.idMesa = idMesa;
        this.nroMesa = nroMesa;
        this.cantidadSillas = cantidadSillas;
        this.estado = estado;
        this.local = local;
    }
    /**
     * Obtiene el identificador único de la mesa.
     *
     * @return el id de la mesa
     */
    public Long getIdMesa() {
        return idMesa;
    }
    /**
     * Establece el identificador único de la mesa.
     *
     * @param idMesa el nuevo id de la mesa
     */
    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }
    /**
     * Obtiene el número de la mesa.
     *
     * @return el número de la mesa
     */
    public int getNroMesa() {
        return nroMesa;
    }
    /**
     * Establece el número de la mesa.
     *
     * @param nroMesa el nuevo número de la mesa
     */
    public void setNroMesa(int nroMesa) {
        this.nroMesa = nroMesa;
    }
    /**
     * Obtiene la cantidad de sillas en la mesa.
     *
     * @return la cantidad de sillas
     */
    public int getCantidadSillas() {
        return cantidadSillas;
    }
    /**
     * Establece la cantidad de sillas en la mesa.
     *
     * @param cantidadSillas la nueva cantidad de sillas
     */
    public void setCantidadSillas(int cantidadSillas) {
        this.cantidadSillas = cantidadSillas;
    }
    /**
     * Obtiene el estado de la mesa.
     *
     * @return el estado de la mesa
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Establece el estado de la mesa.
     *
     * @param estado el nuevo estado de la mesa
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Obtiene el local al que pertenece la mesa.
     *
     * @return el local asociado
     */
    public Local getLocal() {
        return local;
    }
    /**
     * Establece el local al que pertenece la mesa.
     *
     * @param local el nuevo local asociado
     */
    public void setLocal(Local local) {
        this.local = local;
    }
}
