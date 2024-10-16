import "@/styles/globals.css";
import { ReactNode } from 'react';
import Sidebar from '@/components/administradorpersonal/sidebar'; // Este es un componente cliente

// Exportación de metadata
export const metadata = {
  title: 'Administrador Restaurante - Dish Delight',
  description: 'Panel de control para administradores de restaurante',
};

export default function RootLayout({
  children,
}: {
  children: ReactNode;
}) {
  return (
    <html lang="es">
      <body>
        <div className="flex h-screen">
          <Sidebar /> {/* Sidebar del cliente */}
          <main className="flex-1 p-8 bg-gray-50">
            {children} {/* Contenido de las páginas */}
          </main>
        </div>
      </body>
    </html>
  );
}
