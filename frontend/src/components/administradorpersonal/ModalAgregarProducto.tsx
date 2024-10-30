'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Local {
  idLocal: number;
  ubicacion: string;
}

interface Producto {
  idComida: string;
  nombreComida: string;
  tipoComida: string;
  descripcion: string;
  precio: number;
  imagenUrl: string;
  local: {
    idLocal: number;
    ubicacion: string;
  };
}

interface ModalAgregarComidaProps {
  onClose: () => void;
  onProductoAgregado: (producto: Producto) => void; // Aquí pasamos el callback para actualizar el listado en la página principal
}

const ModalAgregarComida: React.FC<ModalAgregarComidaProps> = ({ onClose, onProductoAgregado }) => {
  const [nombreComida, setNombreComida] = useState('');
  const [tipoComida, setTipoComida] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [precio, setPrecio] = useState('');
  const [imagenUrl, setImagenUrl] = useState('');
  const [idLocal, setIdLocal] = useState<number | ''>(''); // Para seleccionar el local
  const [locales, setLocales] = useState<Local[]>([]); // Almacenará los locales obtenidos

  // Llamada a la API para obtener los locales
  useEffect(() => {
    const fetchLocales = async () => {
      try {
        const response = await axios.get('http://localhost:8080/local/api/verlocales');
        setLocales(response.data); // Guardar los locales obtenidos
      } catch (error) {
        console.error('Error al obtener los locales:', error);
      }
    };

    fetchLocales();
  }, []);

  const handleAgregarComida = async (e: React.FormEvent) => {
    e.preventDefault();

    const nuevaComida = {
      nombreComida,
      tipoComida,
      descripcion,
      precio: parseFloat(precio),
      imagenUrl,
      local: {
        idLocal, // Relacionamos el local seleccionado
      },
    };

    try {
      const response = await axios.post('http://localhost:8080/comida/api/nueva', nuevaComida);
      const productoCreado = response.data;

      // Usamos el callback onProductoAgregado para pasar el nuevo producto a la página principal
      onProductoAgregado({
        idComida: productoCreado.idComida,
        nombreComida: productoCreado.nombreComida,
        tipoComida: productoCreado.tipoComida,
        descripcion: productoCreado.descripcion,
        precio: productoCreado.precio,
        imagenUrl: productoCreado.imagenUrl,
        local: {
          idLocal: productoCreado.local.idLocal,
          ubicacion: locales.find((local) => local.idLocal === idLocal)?.ubicacion || '',
        },
      });

      onClose(); // Cierra el modal después de agregar la comida
    } catch (error) {
      console.error('Error al agregar comida:', error);
    }
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Agregar Comida</h2>
        
        <form onSubmit={handleAgregarComida}>
          <input
            type="text"
            placeholder="Nombre de la Comida"
            value={nombreComida}
            onChange={(e) => setNombreComida(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          />
          
          {/* Dropdown para seleccionar el tipo de comida */}
          <select
            value={tipoComida}
            onChange={(e) => setTipoComida(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          >
            <option value="">Seleccionar Tipo de Comida</option>
            <option value="Entradas">Entradas</option>
            <option value="Plato Principal">Plato Principal</option>
            <option value="Sopa">Sopa</option>
            <option value="Parrilla">Parrilla</option>
            <option value="Bebidas">Bebidas</option>
            <option value="Postres">Postres</option>
          </select>

          <textarea
            placeholder="Descripción"
            value={descripcion}
            onChange={(e) => setDescripcion(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
          />
          <input
            type="number"
            placeholder="Precio"
            value={precio}
            onChange={(e) => setPrecio(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          />
          <input
            type="text"
            placeholder="URL de la imagen"
            value={imagenUrl}
            onChange={(e) => setImagenUrl(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
          />

          {/* Dropdown para seleccionar el Local */}
          <select
            value={idLocal}
            onChange={(e) => setIdLocal(Number(e.target.value))}
            className="border w-full p-2 mb-4 text-black"
            required
          >
            <option value="">Seleccionar Local</option>
            {locales.map((local) => (
              <option key={local.idLocal} value={local.idLocal}>
                {local.ubicacion}
              </option>
            ))}
          </select>

          <div className="flex justify-end space-x-4">
            <button type="submit" className="bg-green-500 text-white py-2 px-4 rounded">
              Agregar
            </button>
            <button onClick={onClose} className="bg-red-500 text-white py-2 px-4 rounded">
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ModalAgregarComida;
