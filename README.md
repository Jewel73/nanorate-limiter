# NanoRateLimiter

A lightweight, extensible rate-limiting library in Core Java. Designed for developers who want fine-grained control over request throttling with support for multiple algorithms like Token Bucket and Sliding Window.

## Features
- **Modular Design**: Easily swap between rate-limiting algorithms using the Strategy pattern.
- **Thread-Safe**: Built with concurrency in mind using atomic operations and synchronization.
- **Action-Specific Limiting**: Restrict rates for specific actions (e.g., "button_click") with custom rules.
- **Algorithms**:
  - Token Bucket (continuous refill)
  - Sliding Window (rolling time window)
- **Extensible**: Add new algorithms or configurations without modifying core code.

## Why NanoRateLimiter?
Built from scratch to help Java engineers like me (Jewel) learn design patterns, concurrency, and best practices while providing a practical rate-limiting solution. Perfect for APIs, web apps, or any system needing request control.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/jewel73/nanorate-limiter.git
   ```
2. Add it to your project (manual JAR or future Maven support TBD).

## Usage

### Basic Token Bucket Example
Limit to 100 requests per 2 minutes:
```java
RateLimitConfig config = new RateLimitConfig(100, 120_000, "api_call");
RateLimiter limiter = RateLimiterFactory.createTokenBucketLimiter(config);
RateLimiterManager manager = RateLimiterManager.getInstance();
manager.registerLimiter("client1:api_call", limiter);

boolean allowed = manager.allowRequest("client1", "api_call");
System.out.println(allowed ? "Allowed" : "Blocked");
```

### Action-Specific Sliding Window
Reject >10 button clicks in 20 seconds:
```java
RateLimitConfig config = new RateLimitConfig(10, 20_000, "button_click");
RateLimiter limiter = RateLimiterFactory.createSlidingWindowLimiter(config);
RateLimiterManager manager = RateLimiterManager.getInstance();
manager.registerLimiter("client1:button_click", limiter);

for (int i = 1; i <= 15; i++) {
    boolean allowed = manager.allowRequest("client1", "button_click");
    System.out.println("Click " + i + ": " + (allowed ? "Allowed" : "Blocked"));
    Thread.sleep(50);
}
```

## Supported Algorithms
- **Token Bucket**: Continuous refill for smooth rate enforcement.
- **Sliding Window**: Rolling window for precise burst detection (e.g., 100 clicks in 20s).

## Project Structure
```
nanorate-limiter/
├── src/
│   ├── RateLimiter.java          # Core interface
│   ├── RateLimitConfig.java      # Configuration class
│   ├── RateLimiterFactory.java   # Factory for creating limiters
│   ├── RateLimiterManager.java   # Manages multiple limiters
│   ├── TokenBucketRateLimiter.java  # Token Bucket implementation
│   └── SlidingWindowRateLimiter.java # Sliding Window implementation
└── README.md
```

## Contributing
Contributions are welcome! Please:
1. Fork the repo.
2. Create a feature branch (`git checkout -b feature/my-feature`).
3. Commit changes (`git commit -m "Add my feature"`).
4. Push to the branch (`git push origin feature/my-feature`).
5. Open a Pull Request.

Feel free to suggest new algorithms, optimizations, or use cases!

## Roadmap
- Add Leaky Bucket algorithm.
- Support for distributed rate limiting (e.g., Redis integration).
- Maven/Gradle packaging.
- Micro-burst detection (e.g., >10 clicks in 1s).

## License
MIT License - see [LICENSE](LICENSE) for details.

## Author
- **Jewel** - Java Software Engineer  
 * GitHub: [md jewel](https://github.com/jewel73)
 * GitHub: [md jewel linkedin](https://www.linkedin.com/in/md-jewel-/)
 * Email: md.jewel.msg@gmail.com 

Happy throttling!
```
