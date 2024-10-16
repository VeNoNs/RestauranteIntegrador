'use client';
import React, { useState } from 'react';

interface ModalEditarProductoProps {
  producto: {
    id: string;
    nombre: string;
    precio: number;
  };
  onClose: () => void;
  onUpdate: (productoEditado: { id: string; nombre: string; precio: number }) => void;
}

const ModalEditarProducto: React.FC<ModalEditarProductoProps> = ({ producto, onClose, onUpdate }) => {
  const [nombreProducto, setNombreProducto] = useState(producto.nombre);
  const [precioProducto, setPrecioProducto] = useState(producto.precio);

  const handleEditarProducto = () => {
    const productoEditado = {
      id: producto.id,
      nombre: nombreProducto,
      precio: precioProducto,
    };
    onUpdate(productoEditado);
    onClose();
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Editar Producto</h2>

        <input
          type="text"
          placeholder="Nombre del Producto"
          value={nombreProducto}
          onChange={(e) => setNombreProducto(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        />
        <input
          type="number"
          placeholder="Precio del Producto"
          value={precioProducto}
          onChange={(e) => setPrecioProducto(Number(e.target.value))}
          className="border w-full p-2 mb-4 text-black"
        />

        <div className="flex justify-end space-x-4">
          <button onClick={handleEditarProducto} className="bg-black text-white py-2 px-4 rounded">
            Guardar
          </button>
          <button onClick={onClose} className="bg-gray-500 text-white py-2 px-4 rounded">
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalEditarProducto;
