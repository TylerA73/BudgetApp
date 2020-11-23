import { Component, Fragment } from 'react';
import { Container, Row, Col, Form, Card, Table, Button } from 'react-bootstrap';
import axios from 'axios';

export default class Transactions extends Component {
	constructor(props) {
		super(props);

		let date = new Date();
		let d = date.getDate();
		let m = date.getMonth() + 1;
		let y = date.getFullYear();

		this.state = {
			from: `${y}-${m}-${d}`,
			to: `${y}-${m}-${d}`,
			transactions: [],
			description: null,
			amount: null,
			category: "0", 
			date: null
		}

		this.onChangeFrom = this.onChangeFrom.bind(this);
		this.onChangeTo = this.onChangeTo.bind(this);
		this.getTransactions = this.getTransactions.bind(this);
		this.displayTransactions = this.displayTransactions.bind(this);
		this.addTransaction = this.addTransaction.bind(this);
	}

	onChangeFrom(e) {
		this.setState({ from: e.target.value });
		this.getTransactions(this.state.from, this.state.to);
	}

	onChangeTo(e) {
		this.setState({ to: e.target.value });
		this.getTransactions(this.state.from, this.state.to);
	}

	getTransactions(from, to) {
		axios.get(`http://localhost:8080/transactions/dates?from=${from}&to=${to}`)
		.then((response) => {
			this.setState({ transactions: response.data });
			return axios.get(`http://localhost:8080/transactions/amounts?from=${from}&to=${to}`)
		})
		.then((response) => {
			this.props.callback(response.data);
		})
		.catch((err) => {
			this.setState({transactions: []});
			this.props.callback([]);
			console.error(err);
		})
	}

	addTransaction() {
		if (this.state.description && this.state.amount && this.state.date && this.state.category) {
			if (this.state.category <= 0) {
				console.warn("Bad cat");
			} else {
				axios.post("http://localhost:8080/transactions/add", {
					description: this.state.description,
					amount: this.state.amount,
					category: parseInt(this.state.category),
					date: this.state.date
				}, {
					headers: {
						'Content-Type': 'application/json'
					}	
				})
				.then((response) => {
					return this.getTransactions(this.state.from, this.state.to);
				})
				.catch((err) => {
					this.setState({transactions: []});
					console.error(err);
				})
			}
		} else {
			console.warn("No good");
		}
	}

	displayTransactions() {
		if (this.state.transactions.length <= 0) {
			return [];
		} else {
			return this.state.transactions.map((transaction, index) => {
				return (
					<tr>
						<td>{transaction.description}</td>
						<td>${transaction.amount}</td>
						<td>{transaction.category.description}</td>
						<td>{transaction.date}</td>
					</tr>
				);
			})
		}
	}

	render() {
		console.warn(this.state)
		return (
			<Container>
				<Row>
					<Col>
						<Card className = "budget-card">
							<Card.Body>
								<Row>
									<Col>
										<Form>
											<Form.Row>
												<Col xs = {12} sm = {12} md = {6} lg = {6}>
													<Form.Label>From</Form.Label>
													<Form.Control type = "date" 
														onChange = {this.onChangeFrom}
													/>
												</Col>
												<Col xs = {12} sm = {12} md = {6} lg = {6}>
													<Form.Label>To</Form.Label>
													<Form.Control type = "date" 
														onChange = {this.onChangeTo}
													/>
												</Col>
											</Form.Row>
										</Form>
									</Col>
								</Row>
								<Row>
									<Col>
										<Table>
											<thead>
												<tr>
													<td>Description</td>
													<td>Amount</td>
													<td>Category</td>
													<td>Date</td>
												</tr>
											</thead>
											<tbody>
												{ this.displayTransactions().length > 0 ? this.displayTransactions().map((transaction, index) => {
													return (
														<Fragment key = {`transaction-${index}`}>
															{ transaction }
														</Fragment>
													)
												}) : <td colSpan = "4">No transactions to display</td>}
											</tbody>
										</Table>
									</Col>
								</Row>
								<Row>
									<Col xs = {12} sm = {12} md = {10} lg = {10}>
										<Row>
											<Col xs = {12} sm = {12} md = {6} lg = {6}>
												<Form.Label>Description</Form.Label>
												<Form.Control 
													onChange = {(e) => {
														this.setState({description: e.target.value});
													}}
												/>
											</Col>
											<Col xs = {12} sm = {12} md = {6} lg = {6}>
												<Form.Label>Amount</Form.Label>
												<Form.Control 
													onChange = {(e) => {
														this.setState({amount: e.target.value});
													}}/>
											</Col>
										</Row>
										<Row>
											<Col xs = {12} sm = {12} md = {6} lg = {6}>
												<Form.Group>
													<Form.Label>Category</Form.Label>
													<Form.Control 
														as = "select"
														onChange = {(e) => {
															this.setState({category: e.target.value});
														}}
														defaultValue = {0}
													>
														<option value = {0}>None</option>
														<option value = {1}>Income</option>
														<option value = {2}>Essential</option>
														<option value = {3}>Non-Essential</option>
													</Form.Control>
												</Form.Group>
											</Col>
											<Col xs = {12} sm = {12} md = {6} lg = {6}>
												<Form.Label>Date</Form.Label>
												<Form.Control 
													type = "date"
													onChange = {(e) => {
														this.setState({date: e.target.value});
													}}
												/>
											</Col>
										</Row>
									</Col>
									<Col xs = {12} sm = {12} md = {2} lg = {2}>
										<Button
											onClick = {this.addTransaction}
										>
											Add
										</Button>
									</Col>
								</Row>
							</Card.Body>
						</Card>
					</Col>
				</Row>
			</Container>
		);
	}
}
