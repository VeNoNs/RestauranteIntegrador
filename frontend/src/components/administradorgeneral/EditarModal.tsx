import React, { useState } from 'react';

interface EditarModalProps {
  closeModal: () => void;
  empresa: Empresa;
  onEmpresaEditada: (empresa: Empresa) => void; // Callback para actualizar la empresa en el estado
}

interface Empresa {
  id: number;
  nombre: string;
  telefono: string;
  correo: string;
}

const EditarModal: React.FC<EditarModalProps> = ({ closeModal, empresa, onEmpresaEditada }) => {
  const [nombreEmpresa, setNombreEmpresa] = useState(empresa.nombre);
  const [telefono, setTelefono] = useState(empresa.telefono);
  const [correo, setCorreo] = useState(empresa.correo);

  // Función para manejar la edición
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const empresaEditada = {
      idEmpresa: empresa.id,  // Asegúrate de usar el ID correcto
      nombreEmpresa: nombreEmpresa,
      telefono: telefono,
      usuario: correo,  // Mapeo de correo al campo `usuario`
    };

    try {
      const response = await fetch(`http://localhost:8080/administradorgeneral/api/editar/${empresa.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(empresaEditada),
      });

      if (!response.ok) {
        throw new Error('Error al actualizar la empresa');
      }

      const empresaActualizada = await response.json();

      // Mapear correctamente el objeto que viene del backend
      const empresaFormateada = {
        id: empresaActualizada.idEmpresa,
        nombre: empresaActualizada.nombreEmpresa,
        telefono: empresaActualizada.telefono,
        correo: empresaActualizada.usuario, 
      };

      onEmpresaEditada(empresaFormateada); // Actualiza el estado en el componente padre
      closeModal(); 
    } catch (error) {
      console.error('Error al actualizar empresa:', error);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="relative bg-white rounded-lg p-8 w-96">
        {/* Botón de Cerrar */}
        <button
          onClick={closeModal}
          className="absolute top-2 right-2 bg-red-500 text-white rounded-md px-2 hover:bg-red-600"
        >
          &times;
        </button>
        <h2 className="text-2xl font-bold mb-4">Editar Empresa</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            id="nombreempresa"
            placeholder="Nombre de la empresa"
            value={nombreEmpresa}
            onChange={(e) => setNombreEmpresa(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
          />
          <input
            type="text"
            id="telefono"
            placeholder="Teléfono"
            value={telefono}
            onChange={(e) => setTelefono(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
          />
          <input
            type="email"
            id="correo"
            placeholder="Correo"
            value={correo}
            onChange={(e) => setCorreo(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
          />
          <button type="submit" className="bg-black text-white px-4 py-2 rounded-full w-full hover:bg-gray-800">
            Guardar Cambios
          </button>
        </form>
      </div>
    </div>
  );
};

export default EditarModal;



