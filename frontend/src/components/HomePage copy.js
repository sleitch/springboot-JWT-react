import React from 'react';
import { AppBar, Toolbar, Typography, Button, Container, Box, Grid, Paper } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

export default function HomePage() {
  return (
    <>
      <AppBar position="static" color="primary" elevation={2}>
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>
            User One Eve
          </Typography>
          <Button color="inherit" component={RouterLink} to="/login">
            Login
          </Button>
          <Button color="inherit" component={RouterLink} to="/register">
            Register
          </Button>
          <Button color="inherit" component={RouterLink} to="/forgot-password">
            Forgot Password
          </Button>
        </Toolbar>
      </AppBar>
      <Container maxWidth="md" sx={{ mt: 6 }}>
        <Box sx={{ textAlign: 'center', mb: 4 }}>
          <Typography variant="h3" gutterBottom>
            Welcome to User One Eve
          </Typography>
          <Typography variant="h6" color="text.secondary">
            Your all-in-one platform for user management, secure authentication, and profile customization.
          </Typography>
        </Box>
        <Grid container spacing={4}>
          <Grid item xs={12} md={6}>
            <Paper elevation={3} sx={{ p: 3 }}>
              <Typography variant="h5" gutterBottom>
                🚀 Quick Start
              </Typography>
              <Typography>
                <ul>
                  <li>Register a new account in seconds</li>
                  <li>Login securely with your credentials</li>
                  <li>Reset your password anytime</li>
                </ul>
              </Typography>
            </Paper>
          </Grid>
          <Grid item xs={12} md={6}>
            <Paper elevation={3} sx={{ p: 3 }}>
              <Typography variant="h5" gutterBottom>
                🎨 Features
              </Typography>
              <Typography>
                <ul>
                  <li>Modern, responsive UI with Material-UI</li>
                  <li>Profile management and customization</li>
                  <li>Secure backend powered by Spring Boot</li>
                </ul>
              </Typography>
            </Paper>
          </Grid>
        </Grid>
        <Box sx={{ mt: 6, textAlign: 'center', color: 'text.secondary' }}>
          <Typography variant="body2">
            Need help? Visit our <RouterLink to="/help">Help Center</RouterLink> or contact support.
          </Typography>
        </Box>
      </Container>
    </>
  );
}