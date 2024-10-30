'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface ModalAgregarEmpleadoProps {
  onClose: () => void;
}

const ModalAgregarEmpleado: React.FC<ModalAgregarEmpleadoProps> = ({ onClose }) => {
  const [nombreEmpleado, setNombreEmpleado] = useState('');
  const [apellidoEmpleado, setApellidoEmpleado] = useState('');
  const [tipoEmpleado, setTipoEmpleado] = useState('');
  const [correoEmpleado, setCorreoEmpleado] = useState('');
  const [passwordEmpleado, setPasswordEmpleado] = useState('');
  const [idLocal, setIdLocal] = useState<number | ''>(''); // Para seleccionar el local
  const [locales, setLocales] = useState<{ idLocal: number; ubicacion: string }[]>([]); // Para almacenar los locales disponibles

  // Fetch de locales al cargar el modal
  useEffect(() => {
    const fetchLocales = async () => {
      try {
        const response = await axios.get('http://localhost:8080/local/api/verlocales');
        setLocales(response.data); // Setear los locales disponibles para seleccionarlos
      } catch (error) {
        console.error('Error al obtener los locales:', error);
      }
    };

    fetchLocales();
  }, []);

  const handleAgregarEmpleado = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!nombreEmpleado || !apellidoEmpleado || !tipoEmpleado || !correoEmpleado || !passwordEmpleado || !idLocal) {
      console.error('Todos los campos son obligatorios');
      return;
    }

    const nuevoEmpleado = {
      nombreEmpleado,
      apellidoEmpleado,
      tipoEmpleado,
      correo: correoEmpleado,
      password: passwordEmpleado,
      local: {
        idLocal: idLocal, // Relación con el local seleccionado
      },
    };

    try {
      const response = await axios.post('http://localhost:8080/empleado/api/nuevo', nuevoEmpleado);
      console.log('Empleado creado:', response.data);
      onClose(); // Cierra el modal después de agregar el empleado
    } catch (error) {
      console.error('Error al agregar empleado:', error);
    }
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Agregar Empleado</h2>

        <form onSubmit={handleAgregarEmpleado}>
          <input
            type="text"
            placeholder="Nombre del Empleado"
            value={nombreEmpleado}
            onChange={(e) => setNombreEmpleado(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          />

          <input
            type="text"
            placeholder="Apellido del Empleado"
            value={apellidoEmpleado}
            onChange={(e) => setApellidoEmpleado(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          />

          <input
            type="email"
            placeholder="Correo del Empleado"
            value={correoEmpleado}
            onChange={(e) => setCorreoEmpleado(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          />

          <input
            type="password"
            placeholder="Contraseña"
            value={passwordEmpleado}
            onChange={(e) => setPasswordEmpleado(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          />

          <select
            value={tipoEmpleado}
            onChange={(e) => setTipoEmpleado(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          >
            <option value="">Seleccionar Rol</option>
            <option value="Cocina">Cocina</option>
            <option value="Mozo">Mozo</option>
            <option value="Administrador">Administrador</option>
          </select>

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

export default ModalAgregarEmpleado;
