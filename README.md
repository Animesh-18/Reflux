# 🚀 Event Bus Framework – Full Workflow Documentation

## 📌 Overview

The Event Bus Framework is a **decoupled communication system** that enables different components of an application to communicate via events without direct dependencies.

Instead of components calling each other directly, they:

* **Publish events** → when something happens
* **Subscribe to events** → to react to those changes

This improves:

* Scalability
* Maintainability
* Modularity

---

## 🧠 Core Concepts

### 1. Event

An **Event** represents a change or action in the system.

Example:

```java
class UserCreatedEvent {
    String username;
}
```

---

### 2. Publisher (Producer)

A **Publisher** emits events into the Event Bus.

Example:

```java
eventBus.publish(new UserCreatedEvent("Animesh"));
```

---

### 3. Subscriber (Consumer)

A **Subscriber** listens for specific events and reacts.

Example:

```java
@Subscribe
public void onUserCreated(UserCreatedEvent event) {
    System.out.println("User created: " + event.username);
}
```

---

### 4. Event Bus (Core Engine)

The **Event Bus**:

* Registers subscribers
* Maintains mapping of event types → listeners
* Dispatches events to appropriate subscribers

---

## 🔁 Complete Workflow (Step-by-Step)

### 🟢 Step 1: System Initialization

* Create an instance of EventBus
* Initialize internal data structures

```java
EventBus eventBus = new EventBus();
```

Internal structure:

```
Map<Class<?>, List<Subscriber>>
```

---

### 🟢 Step 2: Register Subscribers

* Subscribers are registered with the EventBus
* Reflection is used to detect methods annotated with `@Subscribe`

```java
eventBus.register(new UserService());
```

#### What happens internally:

1. Scan all methods of the class
2. Identify methods with `@Subscribe`
3. Extract event type from method parameter
4. Store mapping:

```
UserCreatedEvent → [UserService.onUserCreated]
```

---

### 🟢 Step 3: Event Creation

* Event object is created when an action occurs

```java
UserCreatedEvent event = new UserCreatedEvent("Animesh");
```

---

### 🟢 Step 4: Publishing the Event

* Publisher sends event to EventBus

```java
eventBus.publish(event);
```

---

### 🟢 Step 5: Event Routing

Inside EventBus:

1. Identify event type:

```
event.getClass()
```

2. Lookup subscribers:

```
List<Subscribers> = map.get(UserCreatedEvent.class)
```

---

### 🟢 Step 6: Dispatching the Event

* EventBus invokes all matching subscriber methods

```java
subscriberMethod.invoke(instance, event);
```

Execution flow:

```
Publisher → EventBus → Subscriber(s)
```

---

### 🟢 Step 7: Subscriber Execution

Each subscriber processes the event independently:

```java
public void onUserCreated(UserCreatedEvent event) {
    // business logic
}
```

---

## ⚙️ Internal Architecture

![Event Bus Architecture](./Screenshot 2026-04-01 143855.png)

---

## 🧩 Key Features

### ✔ Loose Coupling

* Publisher doesn’t know subscribers
* Subscribers don’t know publishers

---

### ✔ Dynamic Subscription

* Can register/unregister at runtime

---

### ✔ Multiple Subscribers

* One event → many listeners

---

### ✔ Type Safety

* Events are strongly typed

---

## 🔄 Advanced Workflow (Optional Enhancements)

### 1. Async Event Handling

Instead of direct invocation:

```java
ExecutorService.submit(() -> method.invoke(...));
```

---

### 2. Priority-Based Execution

Subscribers can have priority levels:

```
HIGH → MEDIUM → LOW
```

---

### 3. Sticky Events

Store last event and deliver to new subscribers

---

### 4. Error Handling

Wrap invocation:

```java
try {
    method.invoke(...)
} catch (Exception e) {
    // log error
}
```

---

## 🛠 Example Full Flow

### Step 1: Define Event

```java
class OrderPlacedEvent {
    int orderId;
}
```

---

### Step 2: Create Subscriber

```java
class NotificationService {
    @Subscribe
    public void sendNotification(OrderPlacedEvent event) {
        System.out.println("Order placed: " + event.orderId);
    }
}
```

---

### Step 3: Initialize the Bus

```java
eventBus.register(new NotificationService());
```

---

### Step 4: Publish Event

```java
eventBus.publish(new OrderPlacedEvent(101));
```

---

### Step 5: Output

```
Order placed: 101
```

---

## 📊 Advantages

* Clean architecture
* Easy debugging
* Scalable system
* Reusable components

---

## ⚠️ Limitations

* Debugging event chains can be complex
* Reflection may impact performance
* Hard to trace execution flow

---

## 🧠 When to Use

Use Event Bus when:

* Many components need to communicate
* You want loose coupling
* System is event-driven

Avoid when:

* Simple direct calls are enough
* Performance is extremely critical

---

## 🎯 Conclusion

The Event Bus acts as a **central communication backbone** that:

* Receives events
* Routes them
* Notifies subscribers

This allows building **modular, scalable, and maintainable systems** without tight coupling between components.

---

## ✍️ Author

**Animesh Sharma**

---
