import React, { useState } from 'react';
import axios from 'axios';

interface RegisterModalProps {
  closeModal: () => void;
}

const RegisterModal: React.FC<RegisterModalProps> = ({ closeModal }) => {
  // Estado para los campos del formulario
  const [nombrePersona, setNombrePersona] = useState('');
  const [telefono, setTelefono] = useState('');
  const [usuario, setUsuario] = useState('');
  const [password, setPassword] = useState('');

  // Manejar la solicitud de registro
  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/administrador-general/api/nuevo', {
        nombrePersona,
        telefono,
        usuario,
        password,
      });
      console.log('Administrador General creado:', response.data);
      closeModal(); // Cerrar el modal después del registro exitoso
    } catch (error) {
      console.error('Error al registrar Administrador General:', error);
    }
};


  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="relative bg-white rounded-lg p-8 w-96"> {/* Ajuste de ancho */}
        {/* Botón de Cerrar en la esquina superior derecha */}
        <button
          onClick={closeModal}
          className="absolute top-2 right-2 bg-red-500 text-white rounded-md p-3 hover:bg-red-600"
        >
          &times;
        </button>
        <h2 className="text-2xl font-bold mb-4 text-black">Registrar Administrador General</h2> {/* Letras en negro */}
        {/* Formulario de Registro */}
        <form onSubmit={handleRegister}>
          <input
            type="text"
            placeholder="Nombre completo"
            value={nombrePersona}
            onChange={(e) => setNombrePersona(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
            required
          />
          <input
            type="tel"
            placeholder="Teléfono"
            value={telefono}
            onChange={(e) => setTelefono(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
            required
          />
          <input
            type="text"
            placeholder="Usuario"
            value={usuario}
            onChange={(e) => setUsuario(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
            required
          />
          <input
            type="password"
            placeholder="Contraseña"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
            required
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
