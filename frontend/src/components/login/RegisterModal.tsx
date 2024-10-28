import React, { useState } from 'react';

interface RegisterModalProps {
  closeModal: () => void;
}

const RegisterModal: React.FC<RegisterModalProps> = ({ closeModal }) => {
  const [nombre, setNombre] = useState('');
  const [correo, setCorreo] = useState('');
  const [contrasena, setContrasena] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    // Obtener los usuarios existentes desde localStorage
    const usuariosGuardados = JSON.parse(localStorage.getItem('usuarios') || '[]');

    // Crear un nuevo usuario
    const nuevoUsuario = {
      nombre,
      correo,
      contrasena,
    };

    // Agregar el nuevo usuario al array
    usuariosGuardados.push(nuevoUsuario);

    // Guardar el array actualizado en localStorage
    localStorage.setItem('usuarios', JSON.stringify(usuariosGuardados));

    alert('Registro exitoso');
    closeModal(); // Cerrar el modal
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="relative bg-white rounded-lg p-8 w-96">
        <button
          onClick={closeModal}
          className="absolute top-2 right-2 bg-red-500 text-white rounded-md p-3 hover:bg-red-600"
        >
          &times;
        </button>
        <h2 className="text-2xl font-bold mb-4 text-black">Registrarse</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Nombre completo"
            className="border p-2 mb-4 w-full rounded text-black"
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
          />
          <input
            type="email"
            placeholder="Correo electrónico"
            className="border p-2 mb-4 w-full rounded text-black"
            value={correo}
            onChange={(e) => setCorreo(e.target.value)}
          />
          <input
            type="password"
            placeholder="Contraseña"
            className="border p-2 mb-4 w-full rounded text-black"
            value={contrasena}
            onChange={(e) => setContrasena(e.target.value)}
          />
          <button type="submit" className="bg-black text-white px-4 py-2 rounded-full w-full hover:bg-gray-800">
            Registrarse
          </button>
        </form>
      </div>
    </div>
  );
};

export default RegisterModal;
