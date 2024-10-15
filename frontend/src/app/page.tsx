'use client';
import "@/styles/globals.css";
import React, { useState } from 'react';
import LoginModal from '@/components/login/LoginModal';
import RegisterModal from '@/components/login/RegisterModal';


const HomePage: React.FC = () => {
  const [showLoginModal, setShowLoginModal] = useState(false);
  const [showRegisterModal, setShowRegisterModal] = useState(false);

  const handleLoginClick = () => {
    setShowLoginModal(true);
  };

  const handleRegisterClick = () => {
    setShowRegisterModal(true);
  };

  const closeModal = () => {
    setShowLoginModal(false);
    setShowRegisterModal(false);
  };

  return (
    <div className="flex justify-center items-center h-screen bg-white">
      <div className="w-1/2 flex flex-col items-center">
        <img src="/assets/logo.png" alt="Dish Delight" className="w-40 h-40 mb-5" />
        <h1 className="text-4xl font-bold text-black">Dish Delight</h1> {/* Cambié el color a negro */}
        <p className="text-gray-600 mb-8">Comida deliciosa y especial</p>

        <button
          onClick={handleLoginClick}
          className="bg-orange-500 text-white rounded-full px-8 py-3 mb-4 hover:bg-orange-600 transition"
        >
          Iniciar Sesión
        </button>

        <button
          onClick={handleRegisterClick}
          className="bg-black text-white rounded-full px-8 py-3 hover:bg-gray-800 transition"
        >
          Registrarse
        </button>
      </div>

      <div className="w-1/2">
        <img src="/assets/image.png" alt="Pancakes" className="rounded-lg shadow-lg" />
      </div>

      {/* Modales */}
      {showLoginModal && <LoginModal closeModal={closeModal} />}
      {showRegisterModal && <RegisterModal closeModal={closeModal} />}
    </div>
  );
};

export default HomePage;
