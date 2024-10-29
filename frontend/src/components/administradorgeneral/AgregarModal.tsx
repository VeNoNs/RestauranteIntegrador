import React, { useState } from 'react';

interface AgregarModalProps {
  closeModal: () => void;
  onEmpresaAgregada: (empresa: Empresa) => void;
}

interface Empresa {
  id: number;
  nombre: string;
  telefono: string;
  correo: string;
}

const AgregarModal: React.FC<AgregarModalProps> = ({ closeModal, onEmpresaAgregada }) => {
  const [nombreEmpresa, setNombreEmpresa] = useState('');
  const [telefono, setTelefono] = useState('');
  const [correoEmpresa, setCorreoEmpresa] = useState('');
  const [nombreAdmin, setNombreAdmin] = useState('');  // Para el nombre del administrador
  const [correoAdmin, setCorreoAdmin] = useState('');  // Para el correo del administrador
  const [passwordAdmin, setPasswordAdmin] = useState(''); // Para la contraseña del administrador

  // Función para manejar el submit del formulario
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // Validación de campos obligatorios
    if (!nombreEmpresa || !telefono || !correoEmpresa || !nombreAdmin || !correoAdmin || !passwordAdmin) {
      console.error('Todos los campos son obligatorios');
      return;
    }

    // Datos para crear la empresa
    const nuevaEmpresa = {
      nombreEmpresa,
      telefono,
      usuario: correoEmpresa,
      password: 'defaultPassword', // Valor por defecto para la contraseña de la empresa
    };

    try {
      // Enviar la solicitud para crear la empresa
      const empresaResponse = await fetch('http://localhost:8080/empresa/api/nueva', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(nuevaEmpresa),
      });

      if (!empresaResponse.ok) {
        throw new Error('Error al agregar la empresa');
      }

      const empresaAgregada = await empresaResponse.json();

      // Enviar la solicitud para crear el administrador del restaurante asociado a la empresa
      const adminResponse = await fetch('http://localhost:8080/administrador-restaurante/api/nuevo', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          correo: correoAdmin,
          password: passwordAdmin,
          empresa: { idEmpresa: empresaAgregada.idEmpresa }, // Relacionamos el administrador con la empresa
        }),
      });

      if (!adminResponse.ok) {
        throw new Error('Error al agregar el administrador del restaurante');
      }

      const empresaFormateada: Empresa = {
        id: empresaAgregada.idEmpresa,
        nombre: empresaAgregada.nombreEmpresa,
        telefono: empresaAgregada.telefono,
        correo: empresaAgregada.usuario,
      };

      onEmpresaAgregada(empresaFormateada); // Actualizar la lista de empresas en la página principal
      closeModal();
    } catch (error) {
      console.error('Error al agregar empresa o administrador:', error);
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
        <h2 className="text-2xl font-bold mb-4">Agregar Empresa y Administrador</h2>
        <form onSubmit={handleSubmit}>
          <h3 className="text-xl font-semibold">Información de la Empresa</h3>
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
            placeholder="Correo de la Empresa"
            value={correoEmpresa}
            onChange={(e) => setCorreoEmpresa(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
          />
          <h3 className="text-xl font-semibold">Información del Administrador</h3>
          <input
            type="text"
            id="nombreadmin"
            placeholder="Nombre del administrador"
            value={nombreAdmin}
            onChange={(e) => setNombreAdmin(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
          />
          <input
            type="email"
            id="correoadmin"
            placeholder="Correo del administrador"
            value={correoAdmin}
            onChange={(e) => setCorreoAdmin(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
          />
          <input
            type="password"
            id="passwordadmin"
            placeholder="Contraseña del administrador"
            value={passwordAdmin}
            onChange={(e) => setPasswordAdmin(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
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
