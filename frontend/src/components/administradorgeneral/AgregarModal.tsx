import React, { useState } from 'react';

interface AgregarModalProps {
  closeModal: () => void;
  onEmpresaAgregada: (empresa: Empresa) => void; 
}



interface Empresa {
  id: number;
  nombre: string;
  empresa: string;
  telefono: string;
  correo: string;
}


const AgregarModal: React.FC<AgregarModalProps> = ({ closeModal, onEmpresaAgregada }) => {
  const [nombreEmpresa, setNombreEmpresa] = useState('');
  const [telefono, setTelefono] = useState('');
  const [correo, setCorreo] = useState('');

  // Función para manejar el submit del formulario
  const handleSubmit = async (e: React.FormEvent) => {
      e.preventDefault();
    
      if (!nombreEmpresa || !telefono || !correo) {
        console.error('Todos los campos son obligatorios');
        return;
      }
    
      const nuevaEmpresa = {
        nombreEmpresa,
        telefono,
        usuario: correo, 
        password: 'defaultPassword', // Valor por defecto o permitir que se ingrese
      };
    
      try {
        const response = await fetch('http://localhost:8080/administradorgeneral/api/nueva', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(nuevaEmpresa),
        });
    
        if (!response.ok) {
          throw new Error('Error al agregar la empresa');
        }
    
        const empresaAgregada = await response.json();
        
        // Asegúrate de mapear correctamente el objeto empresa recibido
        const empresaFormateada = {
          id: empresaAgregada.idEmpresa,
          nombre: empresaAgregada.nombreEmpresa,
          telefono: empresaAgregada.telefono,
          correo: empresaAgregada.usuario, // Asegúrate de mapear correctamente el campo correo/usuario
        };
    
        onEmpresaAgregada(empresaFormateada); // Actualizar la lista de empresas en la página principal
        closeModal(); 
      } catch (error) {
        console.error('Error al agregar empresa:', error);
      }
    };
  

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="relative bg-white rounded-lg p-8 w-96">
        {/* Botón de Cerrar en la esquina superior derecha */}
        <button
          onClick={closeModal}
          className="absolute top-2 right-2 bg-red-500 text-white rounded-md px-2 hover:bg-red-600"
        >
          &times;
        </button>
        <h2 className="text-black text-2xl font-bold mb-4">Agregar Empresa</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            id="nombreempresa"
            placeholder="Nombre de la empresa"
            value={nombreEmpresa}
            onChange={(e) => setNombreEmpresa(e.target.value)}
            className="text-black border p-2 mb-4 w-full rounded"
          />
          <input
            type="text"
            id="telefono"
            placeholder="Teléfono"
            value={telefono}
            onChange={(e) => setTelefono(e.target.value)}
            className="text-black border p-2 mb-4 w-full rounded"
          />
          <input
            type="email"
            id="correo"
            placeholder="Correo"
            value={correo}
            onChange={(e) => setCorreo(e.target.value)}
            className="text-black border p-2 mb-4 w-full rounded"
          />
          <button type="submit" className="bg-black text-white px-4 py-2 rounded-full w-full hover:bg-gray-800">
            Agregar
          </button>
        </form>
      </div>
    </div>
  );
};

export default AgregarModal;
