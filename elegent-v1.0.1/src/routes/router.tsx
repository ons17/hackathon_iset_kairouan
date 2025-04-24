import { Suspense, lazy } from 'react';
import { Outlet, RouteObject, createBrowserRouter } from 'react-router-dom';
import paths, { rootPaths } from './paths';

import PageLoader from '../components/loading/PageLoader';
import Splash from 'components/loading/Splash';
import MainLayout from 'layouts/main-layout'; // Importation du layout principal

const App = lazy(() => import('App'));
const AuthLayout = lazy(async () => {
  return Promise.all([import('layouts/auth-layout'), new Promise((resolve) => setTimeout(resolve, 1000))]).then(
    ([moduleExports]) => moduleExports
  );
});

const Error404 = lazy(async () => {
  await new Promise((resolve) => setTimeout(resolve, 500));
  return import('pages/errors/Error404');
});

const Sales = lazy(async () => {
  return Promise.all([import('pages/home/Sales'), new Promise((resolve) => setTimeout(resolve, 500))]).then(
    ([moduleExports]) => moduleExports
  );
});

const ListMember = lazy(async () => {
  return Promise.all([import('pages/member/listMember'), new Promise((resolve) => setTimeout(resolve, 500))]).then(
    ([moduleExports]) => moduleExports
  );
});

const UpdateMember = lazy(async () => {
  return Promise.all([import('pages/member/UpdateAdherent'), new Promise((resolve) => setTimeout(resolve, 500))]).then(
    ([moduleExports]) => moduleExports
  );
});

const Login = lazy(async () => import('pages/authentication/Login'));
const SignUp = lazy(async () => import('pages/authentication/SignUp'));
const ResetPassword = lazy(async () => import('pages/authentication/ResetPassword'));
const ForgotPassword = lazy(async () => import('pages/authentication/ForgotPassword'));

const routes: RouteObject[] = [
  {
    element: (
      <Suspense fallback={<Splash />}>
        <App />
      </Suspense>
    ),
    children: [
      {
        path: '/',
        element: (
          <MainLayout>
            <Suspense fallback={<PageLoader />}>
              <Outlet />
            </Suspense>
          </MainLayout>
        ),
        children: [
          {
            path: paths.home,
            element: <Sales />,
          },
          {
            path: paths.member,
            element: (
              <Suspense fallback={<PageLoader />}>
                <ListMember />
              </Suspense>
            ),
          },
          {
            path: `${paths.member}/update/:id`, // URL pour mettre à jour un membre
            element: (
              <Suspense fallback={<PageLoader />}>
                <UpdateMember />
              </Suspense>
            ),
          },
          {
            path: paths.login,
            element: <Login />,
          },
          {
            path: paths.signup,
            element: <SignUp />,
          },
          {
            path: paths.resetPassword,
            element: <ResetPassword />,
          },
          {
            path: paths.forgotPassword,
            element: <ForgotPassword />,
          },
          {
            path: '*',
            element: <Error404 />,
          },
        ],
      },
    ],
  },
];

const router = createBrowserRouter(routes, { basename: '/elegent' });

export default router;
