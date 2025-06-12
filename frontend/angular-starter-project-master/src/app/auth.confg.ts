// // // auth-config.ts
// // import { AuthConfig } from 'angular-oauth2-oidc';

// // export const authConfig: AuthConfig = {
// //   issuer: 'https://accounts.google.com',
// //   clientId: '628018492993-eqbgvjl09jr0rdpj08d1pu9i1n18citt.apps.googleusercontent.com', // ← yahan actual Google client ID dalni hoti hai
// //   redirectUri: window.location.origin,
// //   scope: 'openid profile email',
// //   responseType: 'token',
  
// //   strictDiscoveryDocumentValidation: false,


// // };
// import { AuthConfig } from 'angular-oauth2-oidc';

// export const authConfig: AuthConfig = {
//   issuer: 'https://accounts.google.com',
//   clientId: '628018492993-eqbgvjl09jr0rdpj08d1pu9i1n18citt.apps.googleusercontent.com',
//   redirectUri: window.location.origin,
//   scope: 'openid profile email',
//   responseType: 'code',  
//   disablePKCE:false,
//   nonceStateSeparator:'semicolon',

//  // ← Change 'token' to 'code'

//   strictDiscoveryDocumentValidation: false,
  
// };