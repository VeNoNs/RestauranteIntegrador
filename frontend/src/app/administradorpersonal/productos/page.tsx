'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ModalAgregarProducto from '@/components/administradorpersonal/ModalAgregarProducto';
import ModalEliminarProducto from '@/components/administradorpersonal/ModalEliminarProducto';
import ModalEditarProducto from '@/components/administradorpersonal/ModalEditarProducto';
import { Producto } from '@/types/producto'; 

const ProductosPage: React.FC = () => {
  const [isAgregarModalOpen, setIsAgregarModalOpen] = useState(false);
  const [isEliminarModalOpen, setIsEliminarModalOpen] = useState(false);
  const [isEditarModalOpen, setIsEditarModalOpen] = useState(false);
  const [selectedProducto, setSelectedProducto] = useState<Producto | null>(null);

  const [categoriaSeleccionada, setCategoriaSeleccionada] = useState('Entradas');
  const [productos, setProductos] = useState<Producto[]>([]);

  const fetchProductos = async () => {
    try {
      const response = await axios.get('http://localhost:8080/comida/api/vercomidas');
      setProductos(response.data);
    } catch (error) {
      console.error('Error al obtener productos:', error);
    }
  };

  useEffect(() => {
    fetchProductos();
  }, []);

  const handleAgregarClick = () => {
    setIsAgregarModalOpen(true);
  };

  const handleEliminarClick = () => {
    setIsEliminarModalOpen(true);
  };

  const handleEditarClick = (producto: Producto) => {
    setSelectedProducto(producto);
    setIsEditarModalOpen(true);
  };

  const handleUpdateProducto = async (productoEditado: Producto) => {
    try {
      await axios.put(`http://localhost:8080/comida/api/editar/${productoEditado.idComida}`, productoEditado);
      setProductos((prevProductos) =>
        prevProductos.map((producto) =>
          producto.idComida === productoEditado.idComida ? productoEditado : producto
        )
      );
      setIsEditarModalOpen(false);
    } catch (error) {
      console.error('Error al editar el producto:', error);
    }
  };

  const handleAgregarProducto = (nuevoProducto: Producto) => {
    setProductos((prevProductos) => [...prevProductos, nuevoProducto]);
    setIsAgregarModalOpen(false);
  };

  const handleEliminarProducto = (productoId: string) => {
    setProductos((prevProductos) => prevProductos.filter((producto) => producto.idComida !== productoId));
    setIsEliminarModalOpen(false);
  };

  const productosFiltrados = productos.filter((producto) => producto.tipoComida === categoriaSeleccionada);

  return (
    <div>
      <h1 className="text-3xl font-bold text-black mb-4">Productos</h1>

      <div className="flex justify-center mb-6 space-x-4">
        {['Entradas', 'Plato Principal', 'Sopa', 'Parrilla', 'Bebidas', 'Postres'].map((categoria) => (
          <button
            key={categoria}
            onClick={() => setCategoriaSeleccionada(categoria)}
            className={`py-2 px-4 border-b-2 ${
              categoriaSeleccionada === categoria
                ? 'border-red-500 text-red-500'
                : 'border-transparent text-black hover:text-red-500'
            }`}
          >
            {categoria}
          </button>
        ))}
      </div>

      <div className="flex justify-end space-x-4 mb-4">
        <button onClick={handleAgregarClick} className="bg-green-500 text-white py-2 px-4 rounded-md shadow-md hover:bg-green-700">
          Agregar
        </button>

        <button onClick={handleEliminarClick} className="bg-red-500 text-white py-2 px-4 rounded-md shadow-md hover:bg-red-700">
          Eliminar
        </button>
      </div>

      <div className="grid grid-cols-4 gap-4">
        {productosFiltrados.length > 0 ? (
          productosFiltrados.map((producto) => (
            <div key={producto.idComida} className="border p-4 rounded">
              <img src={producto.imagenUrl} alt={producto.nombreComida} className="w-full h-40 object-cover rounded" />
              <h2 className="mt-2 text-center text-lg text-black">{producto.nombreComida}</h2>
              <p className="text-center text-black">S/. {producto.precio.toFixed(2)}</p>
              <button
                onClick={() => handleEditarClick(producto)}
                className="w-full mt-2 bg-red-100 text-red-500 py-2 px-4 rounded"
              >
                ✏️ Edit dish
              </button>
            </div>
          ))
        ) : (
          <p className="text-center col-span-4">No hay productos en esta categoría.</p>
        )}
      </div>

      {isAgregarModalOpen && (
        <ModalAgregarProducto
          onClose={() => setIsAgregarModalOpen(false)}
          onProductoAgregado={handleAgregarProducto}
        />
      )}

      {isEliminarModalOpen && (
        <ModalEliminarProducto
          onClose={() => setIsEliminarModalOpen(false)}
          onProductoEliminado={handleEliminarProducto}
        />
      )}

      {isEditarModalOpen && selectedProducto && (
        <ModalEditarProducto
          producto={selectedProducto}
          onClose={() => setIsEditarModalOpen(false)}
          onUpdate={handleUpdateProducto}
        />
      )}
    </div>
  );
};

export default ProductosPage;
