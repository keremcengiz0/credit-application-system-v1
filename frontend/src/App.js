import "./App.css";
import Navbar from "./components/Navbar/Navbar";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import CreateACustomerAndMakeAnApplicationForm from "./components/Application/CreateACustomerAndMakeAnApplicationForm";
import MakeAnApplicationForm from "./components/Application/MakeAnApplicationForm";
import GetStatus from "./components/Application/GetStatus";
import GetCustomer from "./components/Customer/GetCustomer";
import UpdateCustomer from "./components/Customer/UpdateCustomer";
import Home from "./components/Home/Home";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar></Navbar>
        <Routes>
          <Route exact path="" element={<Home />}></Route>
          <Route
            exact
            path="/applications/create-customer-and-make-application"
            element={<CreateACustomerAndMakeAnApplicationForm />}
          ></Route>
          <Route
            exact
            path="/applications/make-application"
            element={<MakeAnApplicationForm />}
          ></Route>
          <Route
            exact
            path="/applications/get-status"
            element={<GetStatus />}
          ></Route>
          <Route exact path="/customers" element={<GetCustomer />}></Route>
          <Route
            exact
            path="/customers/update/:id"
            element={<UpdateCustomer />}
          ></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
