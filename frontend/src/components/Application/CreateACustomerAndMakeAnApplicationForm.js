import React, { useState } from "react";
import {
  Stepper,
  Step,
  StepLabel,
  Button,
  Typography,
  TextField,
  Snackbar,
} from "@material-ui/core";
import Axios from "axios";
import { makeStyles } from "@material-ui/core/styles";
import { useNavigate } from "react-router-dom";
import MuiAlert from "@material-ui/lab/Alert";
import CircularProgress from "@mui/material/CircularProgress";

const useStyles = makeStyles((theme) => ({
  textfield: { marginTop: 20 },
}));

function CreateACustomerAndMakeAnApplicationForm() {
  const [activeStep, setActiveStep] = useState(0);
  const classes = useStyles();
  let navigate = useNavigate();
  const [alertOpen, setAlertOpen] = useState(false);
  const [alertType, setAlertType] = useState("success");
  const [alertMessage, setAlertMessage] = useState("");
  const [identityNumberError, setIdentityNumberError] = useState(false);
  const [phoneNumberError, setPhoneNumberError] = useState(false);
  const [progress, setProgress] = useState(false);

  const [stateCustomer, setStateCustomer] = useState({
    identityNumber: "",
    firstName: "",
    lastName: "",
    phoneNumber: "",
    birthDate: "",
  });
  const [stateApplication, setStateApplication] = useState({
    identityNumber: "",
    salary: "",
    guarantee: "",
  });
  const steps = ["Create a Customer", "Make an Application"];

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleChange = (event) => {
    const { name, value } = event.target;

    if (name === "identityNumber") {
      if (value.length !== 11) {
        setIdentityNumberError(true);
      } else {
        setIdentityNumberError(false);
      }
    }

    if (name === "phoneNumber") {
      if (value.length !== 10) {
        setPhoneNumberError(true);
      } else {
        setPhoneNumberError(false);
      }
    }

    setStateCustomer({ ...stateCustomer, [name]: value });
  };

  const handleSubmit = async () => {
    setProgress(true);
    const customerData = { ...stateCustomer };
    try {
      const response = await Axios.post("/customers", customerData);
      if (response.status === 200) {
        setStateApplication({
          ...stateApplication,
          identityNumber: customerData.identityNumber,
        });
        setAlertType("success");
        setAlertMessage("The customer has been successfully created");
        setAlertOpen(true);
        setProgress(false);
        setTimeout(() => {
          handleNext();
        }, 2000);
      }
    } catch (error) {
      console.error(error);
      setAlertType("error");
      setAlertMessage("Failed to create customer");
      setAlertOpen(true);
      setProgress(false);
    }
  };

  const handleCloseAlert = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setAlertOpen(false);
  };

  const handleApplicationSubmit = async () => {
    setProgress(true);
    const applicationData = { ...stateApplication };
    try {
      const response = await Axios.post(
        `/applications/${applicationData.identityNumber}`,
        applicationData
      );
      if (response.status === 200) {
        setAlertType("success");
        setAlertMessage("Application completed successfully");
        setAlertOpen(true);
        setProgress(false);
        setTimeout(() => {
          navigate("/applications/get-status");
        }, 2000);
      }
    } catch (error) {
      setAlertType("error");
      setAlertMessage("Application failed");
      setAlertOpen(true);
      setProgress(false);
      console.error(error);
    }
  };

  return (
    <div>
      <br />
      <br />
      {progress && (
        <div
          style={{
            position: "fixed",
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            backgroundColor: "rgba(0, 0, 0, 0.5)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <CircularProgress />
        </div>
      )}
      <div style={{ display: "flex", justifyContent: "center" }}>
        <Stepper
          activeStep={activeStep}
          style={{
            width: "80%",
            backgroundColor: "transparent",
            marginLeft: 100,
          }}
        >
          {steps.map((label) => (
            <Step key={label}>
              <StepLabel>{label}</StepLabel>
            </Step>
          ))}
        </Stepper>
      </div>
      <br />
      <br />

      <div>
        {activeStep === 0 && (
          <div>
            <Typography variant="h5">
              <b>Create a Customer</b>
            </Typography>
            <TextField
              label="Identity Number"
              name="identityNumber"
              value={stateCustomer.identityNumber}
              onChange={handleChange}
              className={classes.textfield}
              error={identityNumberError}
              helperText={
                identityNumberError ? "Identity Number must be 11 digits" : null
              }
              inputProps={{ maxLength: 11 }}
            />
            <TextField
              label="Name"
              name="firstName"
              value={stateCustomer.firstName}
              onChange={handleChange}
              className={classes.textfield}
            />
            <TextField
              label="Surname"
              name="lastName"
              value={stateCustomer.lastName}
              onChange={handleChange}
              className={classes.textfield}
            />
            <TextField
              label="Phone Number"
              name="phoneNumber"
              value={stateCustomer.phoneNumber}
              onChange={handleChange}
              className={classes.textfield}
              error={phoneNumberError}
              helperText={
                phoneNumberError
                  ? "Phone number must be 10 digits without leading 0"
                  : null
              }
              inputProps={{ maxLength: 10 }}
            />
            <TextField
              label="Birthdate"
              name="birthDate"
              type="date"
              value={stateCustomer.birthDate}
              onChange={handleChange}
              InputLabelProps={{ shrink: true }}
              className={classes.textfield}
            />
            <Button
              style={{ marginLeft: -100, marginTop: 100 }}
              onClick={handleSubmit}
            >
              <b>CREATE</b>
            </Button>

            <Snackbar
              open={alertOpen}
              autoHideDuration={1500}
              onClose={handleCloseAlert}
            >
              <MuiAlert
                elevation={6}
                variant="filled"
                onClose={handleCloseAlert}
                severity={alertType}
              >
                {alertMessage}
              </MuiAlert>
            </Snackbar>
          </div>
        )}
        {activeStep === 1 && (
          <div>
            <Typography variant="h5">Make an Application</Typography>
            <br />
            <br />
            <TextField
              label="Identity Number"
              name="identityNumber"
              value={stateApplication.identityNumber}
              disabled
            />
            <TextField
              label="Salary"
              name="salary"
              value={stateApplication.salary}
              onChange={(event) =>
                setStateApplication({
                  ...stateApplication,
                  salary: event.target.value,
                })
              }
            />
            <TextField
              label="Guarantee"
              name="guarantee"
              value={stateApplication.guarantee}
              onChange={(event) =>
                setStateApplication({
                  ...stateApplication,
                  guarantee: event.target.value,
                })
              }
            />
            <Button
              onClick={handleApplicationSubmit}
              style={{ marginLeft: 25, marginTop: 20 }}
            >
              <b>APPLY</b>
            </Button>
            <Snackbar
              open={alertOpen}
              autoHideDuration={1500}
              onClose={handleCloseAlert}
            >
              <MuiAlert
                elevation={6}
                variant="filled"
                onClose={handleCloseAlert}
                severity={alertType}
              >
                {alertMessage}
              </MuiAlert>
            </Snackbar>
          </div>
        )}
      </div>
    </div>
  );
}
export default CreateACustomerAndMakeAnApplicationForm;
