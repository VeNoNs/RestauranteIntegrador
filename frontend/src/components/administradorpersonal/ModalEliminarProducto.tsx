'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Producto {
  idComida: string;
  nombreComida: string;
}

interface ModalEliminarProductoProps {
  onClose: () => void;
  onProductoEliminado: (id: string) => void; // Callback para actualizar la lista de productos en la vista principal
}

const ModalEliminarProducto: React.FC<ModalEliminarProductoProps> = ({ onClose, onProductoEliminado }) => {
  const [searchTerm, setSearchTerm] = useState('');
  const [productos, setProductos] = useState<Producto[]>([]); // Estado para almacenar los productos

  // Llamada a la API para obtener los productos
  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const response = await axios.get('http://localhost:8080/comida/api/vercomidas');
        setProductos(response.data);
      } catch (error) {
        console.error('Error al obtener productos:', error);
      }
    };

    fetchProductos();
  }, []);

  const handleEliminarProducto = async (productoId: string) => {
    try {
      await axios.delete(`http://localhost:8080/comida/api/eliminar/${productoId}`);
      onProductoEliminado(productoId); // Llama al callback para eliminar el producto de la lista principal
      onClose(); // Cierra el modal
    } catch (error) {
      console.error('Error al eliminar producto:', error);
    }
  };

  const filteredProductos = productos.filter((producto) =>
    producto.nombreComida.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Eliminar Producto</h2>

        <input
          type="text"
          placeholder="Buscar producto"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        />

        <ul className="text-black">
          {filteredProductos.length > 0 ? (
            filteredProductos.map((producto) => (
              <li key={producto.idComida} className="mb-2 flex justify-between items-center">
                <span>{producto.nombreComida}</span>
                <button
                  onClick={() => handleEliminarProducto(producto.idComida)}
                  className="bg-red-500 text-white py-1 px-2 rounded"
                >
                  Eliminar
                </button>
              </li>
            ))
          ) : (
            <li>No se encontraron productos</li>
          )}
        </ul>

        <div className="flex justify-end">
          <button onClick={onClose} className="bg-black text-white py-2 px-4 rounded">
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalEliminarProducto;
