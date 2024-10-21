import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

interface LoginModalProps {
  closeModal: () => void;
}

const LoginModal: React.FC<LoginModalProps> = ({ closeModal }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });

      const data = await response.json();

      if (response.ok) {
        localStorage.setItem('token', data.token);
        closeModal();

        // Redirigir según el tipo de usuario basado en el correo
        if (email.includes('@empleado')) {
          navigate('/administradorpersonal');
        } else if (email.includes('@cocina')) {
          navigate('/cocina');
        } else if (email.includes('@cliente')) {
          navigate('/cliente');
        } else {
          navigate('/default'); // Ruta por defecto si no coincide con los anteriores
        }
      } else {
        setError('Email o contraseña incorrectos');
      }
    } catch (error) {
      console.error('Error:', error);
      setError('Ocurrió un error. Inténtalo de nuevo.');
    }
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
        <h2 className="text-2xl font-bold mb-4 text-black">Iniciar Sesión</h2>

        {error && <p className="text-red-500 mb-4">{error}</p>}

        <form onSubmit={handleLogin}>
          <input
            type="email"
            placeholder="Correo electrónico"
            className="border p-2 mb-4 w-full rounded"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="password"
            placeholder="Contraseña"
            className="border p-2 mb-4 w-full rounded"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button
            type="submit"
            className="bg-orange-500 text-white px-4 py-2 rounded-full w-full hover:bg-orange-600"
          >
            Entrar
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginModal;
