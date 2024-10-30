'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Local {
  idLocal: number;
  nroMesa: number; // Número de mesas disponibles en el local
  ubicacion: string;
}

interface ModalAgregarMesaProps {
  onClose: () => void;
}

const ModalAgregarMesa: React.FC<ModalAgregarMesaProps> = ({ onClose }) => {
  const [numeroMesa, setNumeroMesa] = useState<number | ''>('');
  const [cantidadSillas, setCantidadSillas] = useState<number | ''>('');
  const [estado, setEstado] = useState('Disponible');
  const [idLocal, setIdLocal] = useState<number | ''>(''); // Para seleccionar el local al que pertenece la mesa
  const [locales, setLocales] = useState<Local[]>([]); // Lista de locales obtenida de la API
  const [mesasDisponibles, setMesasDisponibles] = useState<number[]>([]); // Mesas disponibles para el local seleccionado

  // Fetch de locales al cargar el modal
  useEffect(() => {
    const fetchLocales = async () => {
      try {
        const response = await axios.get('http://localhost:8080/local/api/verlocales');
        setLocales(response.data);
      } catch (error) {
        console.error('Error al obtener los locales:', error);
      }
    };

    fetchLocales();
  }, []);

  // Actualizar el número de mesas disponibles cuando se seleccione un local
  useEffect(() => {
    if (idLocal !== '') {
      const localSeleccionado = locales.find((local) => local.idLocal === idLocal);
      if (localSeleccionado) {
        const mesas = Array.from({ length: localSeleccionado.nroMesa }, (_, i) => i + 1);
        setMesasDisponibles(mesas); // Generamos las mesas disponibles del local
      } else {
        setMesasDisponibles([]);
      }
    }
  }, [idLocal, locales]);

  const handleAgregarMesa = async (e: React.FormEvent) => {
    e.preventDefault();

    if (numeroMesa === '' || cantidadSillas === '' || idLocal === '') {
      console.error('Todos los campos son obligatorios');
      return;
    }

    const nuevaMesa = {
      nroMesa: numeroMesa,
      cantidadSillas: cantidadSillas,
      estado: estado,
      local: {
        idLocal: idLocal, // Relación con el local
      },
    };

    try {
      const response = await axios.post('http://localhost:8080/api/mesas/nueva', nuevaMesa);
      console.log('Mesa creada:', response.data);
      onClose(); // Cierra el modal tras agregar la mesa
    } catch (error) {
      console.error('Error al agregar mesa:', error);
    }
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Agregar Mesa</h2>
        
        <form onSubmit={handleAgregarMesa}>
          {/* Selección del local */}
          <select
            value={idLocal}
            onChange={(e) => setIdLocal(Number(e.target.value))}
            className="border w-full p-2 mb-4 text-black"
            required
          >
            <option value="">Seleccionar Local</option>
            {locales.map((local) => (
              <option key={local.idLocal} value={local.idLocal}>
                {local.ubicacion} (Mesas: {local.nroMesa})
              </option>
            ))}
          </select>

          {/* Selección del número de mesa según el local */}
          <select
            value={numeroMesa}
            onChange={(e) => setNumeroMesa(Number(e.target.value))}
            className="border w-full p-2 mb-4 text-black"
            required
            disabled={mesasDisponibles.length === 0}
          >
            <option value="">Seleccionar Número de Mesa</option>
            {mesasDisponibles.map((mesa) => (
              <option key={mesa} value={mesa}>
                Mesa {mesa}
              </option>
            ))}
          </select>

          <input
            type="number"
            placeholder="Cantidad de Sillas"
            value={cantidadSillas}
            onChange={(e) => setCantidadSillas(Number(e.target.value))}
            className="border w-full p-2 mb-4 text-black"
            required
          />
          <select
            value={estado}
            onChange={(e) => setEstado(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
          >
            <option value="Disponible">Disponible</option>
            <option value="Ocupada">Ocupada</option>
            <option value="Mantenimiento">En Mantenimiento</option>
          </select>

          <div className="flex justify-end space-x-4">
            <button type="submit" className="bg-green-400 text-white py-2 px-4 rounded">
              Agregar
            </button>
            <button onClick={onClose} className="bg-red-400 text-white py-2 px-4 rounded">
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ModalAgregarMesa;
